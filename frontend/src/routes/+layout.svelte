<script>
	import { general } from '$lib/appState';
	import '../app.css';
	import Header from '$lib/components/Header.svelte';
</script>

<Header />
<main class:darkMode={$general.isDarkMode}>
	<slot />
</main>

<style>
	main {
		/* height: 100vh;
		height: 100svh; */
		width: 100%;
		max-width: 1600px;
		margin: 0 auto;
		padding: var(--sidePadding, 1rem);
		color: var(--primary-txt-color);
		background-color: var(--primary-bg-color);
	}
</style>
