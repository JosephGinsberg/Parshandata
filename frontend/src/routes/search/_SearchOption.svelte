<script lang="ts">
	export let props: searchParam,
	index: number
	
</script>

<div class="option row {props.param}" id={index.toString()}>
	<img class="handle" src="/move.svg" alt="handle" />
	<div>every word containing a ח</div>

	<span style="display: none;">{JSON.stringify(props)}<span>
</div>

<style>
	.option{
		justify-content: flex-start;
		margin-block-end: .5rem;
	}
	.option .handle{
		display: inline-block;
		height: 24px;
		width: 24px;
		margin-inline-end: .5rem;
		cursor: pointer;
	}
</style>