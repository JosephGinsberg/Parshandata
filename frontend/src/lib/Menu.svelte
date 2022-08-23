<script>
	import { createEventDispatcher } from 'svelte'
	import Button from '$lib/Button.svelte'
	import { clickedOutside } from '$lib/clickedOutside'

	const dispatch = createEventDispatcher()
	let showMenu = false
</script>

<div class="container">
	<Button classes="minimal small" icon="more" on:click={() => (showMenu = true)} />

	{#if showMenu}
		<div class="card menu" use:clickedOutside on:outclick={() => (showMenu = false)}>
			<div
				class="option"
				on:click={() => {
					dispatch('click')
					showMenu = false
				}}
			>
				Toggle occurance count
			</div>
			<div class="option">Define distance</div>
			<div class="option">Delete block</div>
		</div>
	{/if}
</div>

<style>
	.container {
		position: static;
		float: right;
	}
	.menu {
		position: absolute;
		right: 0.5rem;
		width: 275px;
		background-color: var(--primary-bg-color);
		z-index: 25;
	}
	.menu .option {
		margin-bottom: calc(var(--topPadding) / 2);
		padding-bottom: calc(var(--topPadding) / 2);
		border-bottom: 2px solid var(--gray-shade-1);
		cursor: pointer;
	}
	.menu .option:last-of-type {
		margin-bottom: 0;
		padding-bottom: 0;
		border-bottom: none;
	}
</style>
