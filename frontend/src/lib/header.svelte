<script lang="ts">
	import Button from '$lib/Button.svelte'
</script>

<header>
	<div class="row">
		<div class="links">
			<a href="../" sveltekit:prefetch>
				<h1 id="pageTitle">Parshandata</h1>
			</a>
			<a href="/search" class="page" sveltekit:prefetch>Search Tanach</a>
			<a href="/about" class="page" sveltekit:prefetch>About</a>
		</div>
		<Button text="" style="secondary" on:click={() => alert('testing')} />
	</div>
</header>

<style>
	header {
		position: relative;
		height: var(--headerHeight);
		/* padding: calc(var(--topPadding) / 1.5) var(--sidePadding); */
		padding: 0 var(--sidePadding);
		box-sizing: border-box;
		justify-content: space-between;
		background-color: var(--defaultBackground);
		/* box-shadow: 0 1px 3px rgb(0 0 0 / 6%), 0 1px 2px rgb(0 0 0 / 12%); */
		box-shadow: 0 0 15px 0 rgb(0 0 0 / 10%);
		z-index: 10;
	}
	header > .row {
		height: var(--headerHeight);
	}
	.links > a {
		display: inline-block;
	}
	#pageTitle {
		color: var(--offBlack);
		margin: 0;
		font-size: 22px;
		line-height: 1.75;
		font-weight: 700;
	}
	.links > a {
		margin-inline-end: 20px;
		padding: 10px 0;
		color: #6e6e80;
		font-size: 0.875rem;
	}
	a.page:hover {
		color: #353740;
	}
</style>
