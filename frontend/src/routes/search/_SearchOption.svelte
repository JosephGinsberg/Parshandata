<script lang="ts">
	import Button from '$lib/button.svelte'

	export let element: searchParam,
	index: number
	
</script>

<div class="option row {element.param}" id={index.toString()}>
	<img class="handle" src="/move.svg" alt="handle" />

	{#if element.param === 'condition'}
		that
		<Button
			classes='small {element.type==='contains'? 'default': 'muted'}'
			text='contains'
			style='margin-inline-start: 8px;'
			on:click={() => element.type='contains'} />
		<Button
			classes='small {element.type==='does not contain'? 'default': 'muted'}'
			text='does not contain'
			style='margin-inline-start: 8px;'
			on:click={() => element.type='does not contain'} />
	{:else if element.param === 'abstract'}
		<div>every word containing a ח</div>
	{:else if element.param === 'distance'}
		<div>every word containing a ח</div>
	{:else if element.param === 'input'}
		<div>every word containing a ח</div>
	{/if}
	<!-- <div>every word containing a ח</div> -->
</div>

<span class='temp'>{element.type} {element.connector} {element.value} {element.count} {element.level}</span>

<style>
	.option{
		margin-block-start: .5rem;
		justify-content: flex-start;
	}
	.option .handle{
		display: inline-block;
		height: 24px;
		width: 24px;
		margin-inline-end: .5rem;
		cursor: pointer;

		opacity: 0;
	}
	.temp{
		display: none;
	}
</style>