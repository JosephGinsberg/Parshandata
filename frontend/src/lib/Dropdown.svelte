<script lang="ts">
	import Icon from "$lib/Icon.svelte"

	export let classes: string = 'default',
	style: string = '',
	text: string
</script>

<div class="container">
	<div class="dropdown row {classes}" {style}>
		<span>{text}</span>
		<Icon name='expand' />
	</div>
	<!-- append passed in children -->
</div>

<style>
	.dropdown{
		height: 2em;
		padding: 0 8px;
		border: solid 1px var(--lightText);
		border-radius: var(--borderRadius);
		cursor: pointer;
	}
	.dropdown ::first-letter{
		text-transform: uppercase;
	}
	.dropdown.small{
		font-size: .875rem;
		padding: 0 .5rem;
	}
	
	/* button{
		position: relative;
		display: inline-flex;
		align-items: center;
		padding: 0 .75rem;
		border: none;
		border-radius: var(--borderRadius);
		text-align: center;
		font-size: 1rem;
		height: 2em;
		white-space: nowrap;
		cursor: pointer;
		-webkit-user-select: none;
		-ms-user-select: none;
		user-select: none;
	}
	button:hover{
		transition: background-color 0.12s ease-out, color 0.12s ease-out;
	} */
</style>