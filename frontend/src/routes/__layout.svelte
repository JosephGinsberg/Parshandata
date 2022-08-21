<script lang="ts">
	import { page } from '$app/stores'
	import Header from '$lib/header.svelte'
	import NotificationBar from '$lib/NotificationBar.svelte'
	import '../app.css'
</script>

<Header />
<NotificationBar path={$page.url.pathname} />
<main>
	<slot />
</main>

<style>
	main {
		/* height: calc(100% - var(--headerHeight)); */
		height: 100%;
		z-index: 1;
	}
</style>
