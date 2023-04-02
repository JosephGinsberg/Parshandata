<script lang="ts">
	export let title = '';
</script>

<div>
	<div class="title" class:hidden={!title}>{title}</div>
	<div>
		<button class="small" class:selected={false}>text</button>
	</div>
</div>

<!-- <button
classes="small {!searchRequest.taamTachton &&
!!searchRequest.books.join('').match(/Genesis|Exodus|Deuteronomy/)
	? 'default'
	: 'muted'}"
text="No"
on:click={() => (searchRequest.taamTachton = false)}
/> -->
<style>
	.title {
		padding-bottom: 0.5rem;
		font-weight: 500;
	}
</style>
