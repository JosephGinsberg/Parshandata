<script lang="ts">
	import Button from '$lib/Button.svelte'
</script>

<header>
	<div class="row">
		<div class="links row">
			<a href="../" class="row" id="logo" sveltekit:prefetch>
				<img src="/favicon.png" alt="logo" />
				<span>Parshandata</span>
			</a>
			<a href="/search" class="page" sveltekit:prefetch>Search Tanach</a>
			<a href="/about" class="page" sveltekit:prefetch>About</a>
		</div>
		<Button classes="minimal small" icon="" text="" />
	</div>
</header>

<style>
	header {
		position: relative;
		height: var(--headerHeight);
		padding: 0 var(--sidePadding);
		box-sizing: border-box;
		justify-content: space-between;
		box-shadow: 0 0 15px 0 rgb(0 0 0 / 10%);
		background-color: var(--defaultBackground);
		z-index: 10;
	}
	header > .row {
		height: var(--headerHeight);
	}
	.links > a:not(.row) {
		display: inline-block;
	}
	a.row img {
		max-height: 26px;
		padding-right: 0.4rem;
	}
	.dark a.row img {
		filter: invert(1);
	}
	#logo {
		color: var(--primary-txt-color);
		font-size: 22px;
		font-weight: 700;
	}
	.links > a {
		margin-inline-end: 20px;
		padding: 10px 0;
		color: #6e6e80;
		color: var(--secondary-txt-color);
		font-size: 0.875rem;
	}
	a.page:hover {
		color: #353740;
	}
</style>
