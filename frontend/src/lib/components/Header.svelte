<script lang="ts">
	import { general } from '$lib/appState';
	import Button from '$lib/ui/Button.svelte';
</script>

<header class:darkMode={$general.isDarkMode}>
	<div class="row">
		<div class="links row">
			<a href="../" class="row" id="logo" data-sveltekit-preload-data>
				<img src="/images/favicon.png" alt="logo" />
				<span>Parshandata</span>
			</a>
			<!-- <a href="/search" class="page" data-sveltekit-preload-data>Search Tanakh</a> -->
			<a href="/about" class="page" data-sveltekit-preload-data>About</a>
		</div>
		<Button classes="minimal small" icon="" text="" />
	</div>
</header>

<style>
	header {
		/* position: -webkit-sticky;
		position: sticky;
		top: 0px; */
		height: var(--headerHeight);
		padding: 0 var(--sidePadding);
		box-sizing: border-box;
		justify-content: space-between;
		/* box-shadow: 0 2 6px 0 rgb(0 0 0 / 45%); for dark mode */
		box-shadow: 0 0 15px 0 rgb(0 0 0 / 10%);
		background-color: var(--primary-bg-color);
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
		color: var(--primary-txt-color);
	}
</style>
