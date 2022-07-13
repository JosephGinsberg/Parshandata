<script lang="ts">
	import SearchOption from "./_SearchOption.svelte"
	import Button from "$lib/Button.svelte"

	export let search: searchRequest | JSON | any,
	updateSearch: Function,
	runSearch: VoidFunction

	let isAdvancedMode: boolean = true,
	request: string = JSON.stringify(search, undefined, 4),
	fileSelector: HTMLInputElement

	const validateJson = (expectedJson: string) => {
		try {
			JSON.parse(expectedJson)
			return true
		} catch {
			return false
		}
	},
	uploadSearch: VoidFunction = async () => {
		if(!fileSelector.files) return

		const savedSearch = await fileSelector.files[0].text()
		request = savedSearch
	},
	downloadSearch: VoidFunction = () => {
		let dataString: string = JSON.stringify(JSON.parse(request), undefined, 4),
		filename: string = 'Search | Parshandata.json'

		// initiate download
		let universalBOM: string = "\uFEFF",
		link: HTMLElement = document.createElement('a')
		link.setAttribute('href', 'data:text/csv;charset=utf-8,' + encodeURIComponent(universalBOM+dataString));
		link.setAttribute('download', filename)
		link.click()
	}

	$: if(validateJson(request.toString())) updateSearch(JSON.parse(request))
	// request = JSON.stringify(JSON.parse(request), undefined, 4)
</script>

<div class="container">
	{#if isAdvancedMode}
		<textarea class:error={!validateJson(request.toString())} bind:value={request} autocomplete="off" spellcheck="false"></textarea>
	{:else}
		{#each search.search as searchOption, i (searchOption)}
			<SearchOption props={searchOption} index={i} />
		{/each}
	{/if}

	<div class="buttonsContainer row">
		<Button style='default' text="Search" on:click={runSearch} />
		<div>
			<Button style='minimal small' icon='save' text='Save search' on:click={downloadSearch} />
			<Button style='minimal small' icon='upload' text='Upload' on:click={() => fileSelector.click()} />
			<input type="file" bind:this={fileSelector} on:change={uploadSearch} accept=".json" />
		</div>
	</div>
</div>

<style>
	.container{
		display: flex;
		flex-direction: column;
		height: 100%;
		max-height: 100%;
		width: 47.5%;
		max-width: 47.5%;
		padding-right: 0;
		box-sizing: border-box;
		/* overflow-y: auto; */
		background-color: var(--defaultBackground);
		border: 2px solid var(--lightText);
		border-radius: var(--borderRadius);
	}
	.container:focus-within, .container:focus-within .buttonsContainer{
		border-color: var(--supportText);
	}
	textarea{
		width: 100%;
		height: 100%;
		background-color: transparent;
		border: none;
		outline: none;
		resize: none;
	}
	textarea.error{
		color: red;
	}
	.buttonsContainer{
		align-items: center;
		padding: calc(var(--topPadding) / 1.5) var(--topPadding);
		border-top: 2px solid var(--lightText);
	}
	input[type=file]{
		display: none;
	}
</style>