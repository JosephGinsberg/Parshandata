<script lang='ts'>
	import { clickedOutside } from '$lib/ui/clickedOutside'

	let editMode = false;
</script>

<!-- svelte-ignore a11y-click-events-have-key-events -->
<div
	use:clickedOutside
	on:click={() => (editMode = true)}
	on:outclick={() => (editMode = false)}
>

</div>