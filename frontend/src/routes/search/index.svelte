<script lang="ts">
	import { globalState } from '../../globalState'
	import SearchContainer from './_SearchContainer.svelte'
	import ResultsContainer from './_ResultsContainer.svelte'
	import ParameterPanel from './_ParameterPanel.svelte'

	let searchRequest: SearchRequest,
		searchStatus: SearchResponse | Promise<SearchResponse> = {
			ok: true,
			msg: '',
			runtime: 0,
			matches: []
		},
		count = '0'
	globalState.subscribe(value => ({ searchRequest } = value))

	const fetchSearch = async () => {
		count = '#'
		const res: any = await fetch(
				window.location.hostname === 'localhost'
					? 'http://localhost:8080/api/search'
					: 'https://parshandata.herokuapp.com/api/search',
				{
					method: 'POST',
					headers: {
						'content-type': 'application/json'
					},
					body: JSON.stringify(searchRequest)
				}
			),
			json = await res.json()
		count = json.matches.length.toString()
		console.log('runtime: %s', json.runtime)
		return json
	}
	const runSearch = () => (searchStatus = fetchSearch())
</script>

<svelte:head>
	<title>Search | Parshandata</title>
	<meta
		name="description"
		content="A tool enabling you to search Tanach the way that you want to."
	/>
</svelte:head>

<section class="fullHeight fullWidth" class:fullWidth={true}>
	<h2 style="text-transform: unset;color: var(--supportText);margin: 0;padding: 1.25rem 0 1rem">
		Search Tanach
	</h2>

	<div class="row">
		<SearchContainer {runSearch} />
		<ResultsContainer state={searchStatus} {count} />
		<ParameterPanel />
	</div>
</section>

<style>
	section {
		padding-top: 0;
		padding-bottom: 0;
	}
	.row {
		align-items: flex-start;
		justify-content: space-between;
		height: 100%;
		width: 100%;
		max-width: 100%;
	}
</style>
