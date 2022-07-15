<script lang="ts">
	import Button from "$lib/Button.svelte";
	import Icon from "$lib/Icon.svelte";

	export let search: searchRequest
	
</script>

<aside>
	<div class="option">
		<h3 class="title">Books to search</h3>
		<div class="tempDropdown row">
			{`${search.books[0]}, ${search.books[1]}, ${search.books[0][0]}...`}
			<Icon name='expand' />
		</div>
		<!-- {JSON.stringify(search.books)} -->
	</div>

	<div class='option'>
		<h3 class="title">Use <i>keri</i> instead of <i>kesiv</i></h3>
		<Button classes='small {search.keriUkesiv? 'default': 'muted'}' text='Yes' />
		<Button classes='small {!search.keriUkesiv? 'default': 'muted'}' text='No' />
		<!-- {search.keriUkesiv} -->
	</div>

	<div class='option'>
		<h3 class="title">Use <i>taam elyon</i></h3>
		<Button classes='small {search.taamTachton? 'default': 'muted'}' text='Yes' />
		<Button classes='small {!search.taamTachton? 'default': 'muted'}' text='No' />
		<!-- {search.taamTachton} -->
	</div>

	<div class="option">
		<h3 class="title">Exclude chachters from search</h3>
		<Button classes='small {search.remove.indexOf('letter') >= 0? 'default': 'muted'}' text='Letter' />
		<Button classes='small {search.remove.indexOf('nekudah') >= 0? 'default': 'muted'}' text='Nekudah' />
		<Button classes='small {search.remove.indexOf('trop') >= 0? 'default': 'muted'}'  text='Trop' />
		<!-- {JSON.stringify(search.remove)} -->
	</div>

	<div class="option">
		<h3 class="title">Display</h3>
		<div class="tempDropdown row">
			{search.display}
			<Icon name='expand' />
		</div>
	</div>

	<div class="option">
		<h3 class="title">Split results by</h3>
		<div class="tempDropdown row">
			{search.splitBy}
			<Icon name='expand' />
		</div>
	</div>
</aside>

<style>
	aside{
		display: grid;
		grid-template-columns: 100%;
		gap: 16px;
		width: 240px;
		padding-inline-start: var(--sidePadding);
		/* padding-inline-end: var(--sidePadding); */
	}
	.title{
		font-weight: 500;
	}
	.tempDropdown{
		padding: 0 8px;
		border: solid 1px var(--lightText);
		border-radius: var(--borderRadius);
		line-height: 32px;
	}
</style>