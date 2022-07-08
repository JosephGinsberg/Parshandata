<script lang="ts">
	import SearchOption from "./_SearchOption.svelte"
	import Button from "$lib/Button.svelte"

	export let search: searchRequest,
	runSearch:VoidFunction
	
	const validJson = (expectedJson: string) => {
		try {
			JSON.parse(expectedJson)
			return true
		} catch {
			return false
		}
	}

	let isAdvancedMode: boolean = true,
	request: string = JSON.stringify(search, undefined, 4)
	// $: if(validJson(request.toString())) search = JSON.parse(request)
</script>

<div class="container">
	{#if isAdvancedMode}
		<textarea class:error={!validJson(request.toString())} bind:value={request} autocomplete="off" spellcheck="false"></textarea>
	{:else}
		{#each search.search as searchOption, i (searchOption)}
			<SearchOption props={searchOption} index={i} />
		{/each}
	{/if}
	<br>
	<Button text="Search" on:click={runSearch} />
</div>

<style>
	.container{
		height: 100%;
		max-height: 100%;
		width: 45%;
		max-width: 45%;
		/* padding: var(--topPadding) 0; */
		padding: var(--topPadding);
		box-sizing: border-box;
		/* overflow-y: auto; */
		background-color: var(--defaultBackground);
		border: 2px solid var(--lightText);
		border-radius: var(--borderRadius);
	}
	.container:focus-within{
		border-color: var(--supportText);
	}
	textarea{
		width: 100%;
		height: calc(100% - 40px);
		/* padding: var(--topPadding) 0; */
		background-color: transparent;
		border: none;
		outline: none;
		resize: none;
	}
	textarea.error{
		color: red;
	}
</style>