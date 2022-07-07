<script lang="ts">
	let prompt: notification = {
		urgency: 1,
		msg: 'Please understand the search engine is still in alpha testing.'
	}
</script>

<style>
	.prompt{
		width: 100%;
		margin-top: 1px;
		padding: var(--topPadding) var(--sidePadding);
		box-sizing: border-box;
		background-color: var(--blue-background);
		color: var(--blue-primary);
	}
	.prompt:first-letter{
		text-transform: capitalize;
	}
	.prompt.warn{
		background-color: var(--orange-background);
		color: var(--orange-primary);
	}
	.prompt.error{
		background-color: var(--red-background);
		color: var(--red-primary);
	}
</style>

<div class="prompt" class:clear={!prompt.msg} class:warn={prompt.urgency===1} class:error={prompt.urgency===2}>
	<div class="widest">{prompt.msg}&nbsp;</div>
</div>