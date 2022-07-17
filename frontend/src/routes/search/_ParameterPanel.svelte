<script lang="ts">
	import Button from "$lib/Button.svelte";
	import Icon from "$lib/Icon.svelte";

	export let search: searchRequest,
	updateSearchByKey: any

	const changeArray: Function = (array: string[], value: string) => {
		if(array.indexOf(value) > -1) array = array.filter(item => item !== value)
		else array.push(value)
		return array
	}
</script>

<aside>
	<div class="option">
		<h3 class="title">Books to search</h3>
		<div class="tempDropdown row">
			{`${search.books[0]}, ${search.books[1]}, ${search.books[0][0]}...`}
			<!-- {search.books.length} Books -->
			<!-- {search.books.length} Selected -->
			<Icon name='expand' />
		</div>
		<!-- {JSON.stringify(search.books)} -->
	</div>

	<div class='option'>
		<h3 class="title">Use <i>keri</i> instead of <i>kesiv</i></h3>
		<Button classes='small {search.keriUkesiv? 'default': 'muted'}' text='Yes' on:click={() => updateSearchByKey('keriUkesiv', true)} />
		<Button classes='small {!search.keriUkesiv? 'default': 'muted'}' text='No' on:click={() => updateSearchByKey('keriUkesiv', false)} />
		<!-- {search.keriUkesiv} -->
	</div>

	<div class='option'>
		<h3 class="title">Use <i>taam tachton</i></h3>
		<Button classes='small {search.taamTachton? 'default': 'muted'}' text='Yes' on:click={() => updateSearchByKey('taamTachton', true)} />
		<Button classes='small {!search.taamTachton? 'default': 'muted'}' text='No' on:click={() => updateSearchByKey('taamTachton', false)} />
		<!-- {search.taamTachton} -->
	</div>

	<div class="option">
		<h3 class="title">Exclude chachter group(s) from search</h3>
		<Button classes='small {search.remove.indexOf('letter') >= 0? 'default': 'muted'}' text='Letter' on:click={updateSearchByKey('remove', changeArray(search.remove, 'letter'))} />
		<Button classes='small {search.remove.indexOf('nekudah') >= 0? 'default': 'muted'}' text='Nekudah' on:click={updateSearchByKey('remove', changeArray(search.remove, 'nekudah'))} />
		<Button classes='small {search.remove.indexOf('trop') >= 0? 'default': 'muted'}'  text='Trop' on:click={updateSearchByKey('remove', changeArray(search.remove, 'trop'))} />
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
		min-width: 240px;
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
		text-transform: capitalize;
	}
</style>