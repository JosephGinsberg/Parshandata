<script lang="ts">
	import { Portal } from '@svelteuidev/core';

	let container: HTMLDivElement;
</script>

<div bind:this={container}>Appear here</div>
<Portal target={container}>My portal</Portal>
