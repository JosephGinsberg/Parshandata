<script lang="ts">
	import { page } from '$app/stores';
	import Header from '$lib/header.svelte';
	import NotificationBar from '$lib/NotificationBar.svelte';
	import '../app.css';
</script>

<Header />
<NotificationBar />
<main>
	<slot />
</main>

<!-- {$page.url.pathname} -->

<style>
	main{
		position: relative;
		min-height: 85%;
		z-index: 1;
	}
</style>