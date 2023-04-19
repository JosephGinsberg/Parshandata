<script lang="ts">
// param: string;
// type: string;
// connector: string;
// value?: string;
// count?: number;
// counttype?: string;
// distancetype?: string;
// matchtype?: string;
// level: number;


	// import { globalState } from '../../globalState'
	import { clickedOutside } from '$lib/ui/clickedOutside'
	// import Menu from '$lib/Menu.svelte'
	// import InputKeyboard from './_InputKeyboard.svelte'

	export let element: SearchParam, index: number;
</script>

<div class="param">
	<div class="option count" class:hidden={!element.type}>{element.type}</div>
	<div class="option changeable">{element.matchtype}</div>
	<!-- <div class="option changeable">{element.type}</div> -->
	{#if element.matchtype != 'is'}
		<div class="option changeable">{element.counttype}</div>
		<div class="option count">{element.count}</div>
	{/if}
	<div class="option count" class:hidden={!element.value}>{element.value}</div>
	<div class="option active" class:hidden={element.connector == 'none'}>{element.connector}</div>
	<!-- {#if element.connector != 'none'}
		<div class="group connector">
			<div class="option" class:selected={element.connector == 'and'}>AND</div>
			<div class="option" class:selected={element.connector == 'or'}>OR</div>
		</div>
	{/if} -->
</div>

<!-- {JSON.stringify(element)} -->

<style>
	.param {
		padding: .25rem .75rem;
	}
	.option, .group {
		display: inline-block;
		padding: calc(.5rem - 2px) .5rem;
		background-color: #e0e0eb;
		background-color: var(--tertiary-bg-color);
		background-color: var(--quaternary-bg-color);
		border: var(--quaternary-bg-color) 2px solid;
		border-radius: .33rem;
		text-transform: uppercase;
		cursor: pointer;
	}
	.group {
		padding: 0;
		border: 0px;
	}
	.option.selected {
		background-color: var(--blue-primary);
		color: var(--primary-bg-color);
	}
	/* .option.count { */
	.option.active {
		background: var(--primary-bg-color);
		border-color: var(--blue-primary);
	}
</style>
