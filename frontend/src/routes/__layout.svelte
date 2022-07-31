<script lang="ts">
	import { page } from '$app/stores'
	import Header from '$lib/header.svelte'
	import NotificationBar from '$lib/NotificationBar.svelte'
	import '../app.css'
</script>

<Header />
<NotificationBar />
<main>
	<slot />
</main>

<!-- {$page.url.pathname} -->
<style>
	main {
		position: relative;
		height: calc(100% - (var(--headerHeight) + 150px));
		z-index: 1;
	}
</style>
