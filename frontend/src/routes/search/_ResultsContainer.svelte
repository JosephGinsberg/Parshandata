<script lang="ts">
	import Button from '$lib/Button.svelte'

	export let state: SearchResponse | Promise<SearchResponse> | any, count: string

	const downloadResults: any | VoidFunction = async () => {
		let dataString: string = 'VERSE,MATCH,BOOK,PEREK,PASUK\n',
			filename: string = 'Results | Parshandata.csv'

		// if(!state) return
		try {
			await state.then((res: SearchResponse) => {
				if (!res.matches) return
				res.matches.forEach((match: SearchMatch) => {
					dataString += match.fullverse + ','
					dataString += match.splitvalue != match.fullverse ? match.splitvalue + ',' : 'N/A,'
					dataString += match.bookname.replace('_', ' ') + ','
					dataString += match.perek + ','
					dataString += match.pasuk + '\n'
				})
			})
		} catch {
			console.error('Error: no results w/ error')
			return
		}

		// initiate download
		let universalBOM: string = '\uFEFF',
			link: HTMLElement = document.createElement('a')
		link.setAttribute(
			'href',
			'data:text/csv;charset=utf-8,' + encodeURIComponent(universalBOM + dataString)
		)
		link.setAttribute('download', filename)
		link.click()
	}
</script>

<div class="container">
	<div class="header row">
		<div>
			Results
			<span class="subtext">• {count}</span>
		</div>
		<div class="options row">
			<Button classes="minimal small" icon="download" text="Download" on:click={downloadResults} />
		</div>
	</div>
	<div class="body">
		{#await state}
			<div class="result">Loading...</div>
		{:then data}
			{#if !data.ok}
				<div class="result" style="color: red">Server error: {data.msg}</div>
			{:else if data.runtime && data.ok}
				{#each data.matches as match, id}
					<div class="result">
						<!-- {data.matches[id-1] < data.matches && match.fullverse === data.matches[id-1].fullverse? true: ''} -->
						<div class="fullverse">
							{#if match.splitvalue}
								<!-- everything is connected to avoid unwanted whitespace -->
								{match.fullverse.split(match.splitvalue)[0]}<b>{match.splitvalue}</b
								>{match.fullverse.split(match.splitvalue).slice(1).join(match.splitvalue)}
							{:else}
								{match.fullverse}
							{/if}
						</div>
						<a
							class="location"
							href="https://www.sefaria.org/{match.bookname.replace(
								'1',
								'I'
							)}.{match.perek}.{match.pasuk}?lang=he"
							target="_blank"
						>
							{#if data.matches[id + 1] && match.fullverse === data.matches[id + 1].fullverse && match.bookname === data.matches[id + 1].bookname && match.perek === data.matches[id + 1].perek && match.pasuk === data.matches[id + 1].pasuk}*{/if}
							{match.bookname.replaceAll('_', ' ')}
							{match.perek}:{match.pasuk}</a
						>
					</div>
				{:else}
					<div class="result">No results found</div>
				{/each}
			{:else}
				<div class="result">No search entered</div>
			{/if}
		{:catch error}
			<div class="result" style="color: red">Local error: {error.message}</div>
		{/await}
	</div>
</div>

<style>
	@font-face {
		font-family: taamey frank;
		src: url('/fonts/TaameyFrankCLM-Medium.ttf');
	}

	@font-face {
		font-family: taamey frank;
		src: url('/fonts/TaameyFrankCLM-Bold.ttf');
		font-weight: 700;
	}

	.container {
		height: 100%;
		max-height: 100%;
		overflow-y: hidden;
		width: calc(50% - var(--sidePadding));
		box-sizing: border-box;
		background-color: var(--secondary-bg-color);
		border: 2px solid var(--gray-shade-2);
		border-radius: var(--borderRadius);
	}
	.container .header {
		padding: calc(var(--topPadding) / 1.5) var(--topPadding);
	}
	.container .body {
		padding: var(--topPadding);
	}
	.container .header {
		border-bottom: 2px solid var(--gray-shade-2);
	}
	.container .body {
		max-height: 84%;
		overflow-y: auto;
	}
	.container .result:not(:last-of-type) {
		padding-bottom: calc(var(--topPadding) / 2);
		border-bottom: 2px solid var(--gray-shade-2);
	}
	.container .result:not(:first-of-type) {
		padding-top: calc(var(--topPadding) / 2);
	}
	.container .fullverse {
		font-family: 'Taamey Frank', 'david', serif;
		font-size: 20px;
		text-align: right;
		padding-bottom: calc(var(--topPadding) / 2.5);
	}
	a {
		color: #005ebc;
		/* color: #0852a8; */
	}
	a:hover {
		color: #07305e;
		/* color: #1065c5; */
		/* text-decoration: underline; */
	}
	a:visited {
		/* color: rgb(0,80,160); */
		color: #07305e;
	}
</style>
