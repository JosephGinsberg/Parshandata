<script lang="ts">
	import { api } from '$lib/api';
	import { search } from '$lib/appState';
	import ParamPanel from '$lib/components/ParamPanel.svelte';
	import ResultsPanel from '$lib/components/ResultsPanel.svelte';
	import SearchPanel from '$lib/components/SearchPanel.svelte';

	let results: SearchResponse | Promise<SearchResponse> = {
		ok: true,
		msg: '',
		runtime: 0,
		matches: []
	};
	let count = '0';

	const runSearch = async (): Promise<SearchResponse> => {
		count = '#';
		const res = await api('POST', 'api/search', $search);
		const json = await res.json();
		count = json.matches.length.toString();
		console.log('runtime: %s', json.runtime);
		return json;
	};
</script>

<svelte:head>
	<title>Search | Parshandata</title>
	<meta
		name="description"
		content="A tool empowering you to search Tanach the way that you want to."
	/>
	<link rel="icon" href="https://parshandata.onrender.com/favicon.png" />
</svelte:head>

<div class="container" style="height: 100%;">
	<SearchPanel runSearch={() => (results = runSearch())} />
	<ResultsPanel state={results} {count} />
	<ParamPanel />
</div>

<style>
	.container {
		display: grid;
		grid-template-columns: minmax(0%, 50%) minmax(0%, 50%) 15rem;
		gap: var(--sidePadding, 2rem);
	}
</style>
