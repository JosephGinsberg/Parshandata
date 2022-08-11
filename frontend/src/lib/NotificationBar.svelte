<script lang="ts">
	export let path: string
	let prompt: notification = {
		urgency: 1,
		msg: 'The search engine is currently in alpha development.'
	}
</script>

<div
	class="prompt"
	class:clear={!prompt.msg || path !== '/search'}
	class:warn={prompt.urgency === 1}
	class:error={prompt.urgency === 2}
>
	<div class:widest={false}>{prompt.msg}&nbsp;</div>
</div>

<style>
	.prompt {
		width: 100%;
		margin-top: 1px;
		padding: calc(var(--topPadding) / 1.3) var(--sidePadding);
		box-sizing: border-box;
		background-color: var(--blue-background);
		color: var(--blue-primary);
	}
	.prompt:first-letter {
		text-transform: capitalize;
	}
	.prompt.warn {
		background-color: var(--orange-background);
		color: var(--orange-primary);
	}
	.prompt.error {
		background-color: var(--red-background);
		color: var(--red-primary);
	}
</style>
