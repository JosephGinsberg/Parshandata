<script lang="ts">
	import Icon from "$lib/Icon.svelte"

	export let text: string
</script>

<div class="container">
	<div class="dropdown row">
		<span>{text}</span>
		<Icon name='expand' />
	</div>
	<!-- append passed in children -->
</div>

<style>
	.dropdown{
		padding: 0 8px;
		border: solid 1px var(--lightText);
		border-radius: var(--borderRadius);
		line-height: 32px;
		text-transform: capitalize;
		cursor: pointer;
	}
</style>