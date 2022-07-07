<script lang="ts">
	import SearchOption from "./_SearchOption.svelte"
	import Button from "$lib/Button.svelte"

	export let search: searchParam[],
	runSearch:any
</script>

<div class="container">
	{#each search as searchOption, i (searchOption)}
		<SearchOption props={searchOption} index={i} />
	{/each}
	<br>
	<Button text="Search" on:click={runSearch} />
</div>

<style>
	.container{
		width: 45%;
		max-width: 45%;
		box-sizing: border-box;
		padding: var(--topPadding) var(--sidePadding);
	}
</style>