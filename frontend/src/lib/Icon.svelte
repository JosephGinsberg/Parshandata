<script lang="ts">
	import { createEventDispatcher } from 'svelte'

	export let name: string,
		width = '24px',
		height = '24px',
		margin = '0px',
		style = ''

	const dispatch = createEventDispatcher()
</script>

<svg {width} {height} style="margin-right: {margin};{style}" on:click={() => dispatch('click')}>
	<use href="/images/icons.svg#{name}" />
</svg>
