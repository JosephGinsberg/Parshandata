<script lang="ts">
	import Button from "$lib/Button.svelte"
	import Dropdown from "$lib/Dropdown.svelte"

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
		<!-- <Dropdown text={`${search.books[0]}, ${search.books[1]}, ${search.books[0][0]}...`} /> -->
		<Dropdown text='{search.books.length} Book{search.books.length > 1? 's': ''} selected' />
		<!-- <Dropdown text='{search.books.length} Book{search.books.length > 1? 's': ''}' /> -->
		<!-- <Dropdown text='{search.books.length} Selected' /> -->
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
		<Button classes='small {search.remove.indexOf('other') >= 0? 'default': 'muted'}'  text='Other' on:click={updateSearchByKey('remove', changeArray(search.remove, 'other'))} />
		<!-- {JSON.stringify(search.remove)} -->
	</div>
	
	<div class="option">
		<h3 class="title">Display</h3>
		<Dropdown text={search.display} />
	</div>
	
	<div class="option">
		<h3 class="title">Split results by</h3>
		<Dropdown text={search.splitBy} />
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
</style>