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
	<link rel="icon" href="https://parshandata.herokuapp.com/images/favicon.png" />
</svelte:head>

<section class="fullHeight fullWidth row" class:fullWidth={true}>
	<div
		style="text-transform: unset;color: var(--secondary-txt-color);margin: 0;padding: 1.25rem 0 1rem"
	>
		Search Tanach
	</div>

	<div class="row">
		<SearchContainer {runSearch} />
		<ResultsContainer state={searchStatus} {count} />
		<ParameterPanel />
	</div>
</section>

<style>
	section {
		position: relative;
		display: flex;
		flex-direction: column;
		max-height: 100%;
		padding: 0 var(--sidePadding) var(--sidePadding);
	}
	.row {
		align-items: flex-start;
		justify-content: space-between;
		height: 100%;
		width: 100%;
		max-width: 100%;
	}
</style>
