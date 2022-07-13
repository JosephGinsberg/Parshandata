<script lang="ts">
	import Button from "$lib/Button.svelte"
</script>

<style>
	header{
		position: relative;
		height: var(--headerHeight);
		/* padding: calc(var(--topPadding) / 1.5) var(--sidePadding); */
		padding: 0 var(--sidePadding);
		box-sizing: border-box;
		justify-content: space-between;
		background-color: var(--defaultBackground);
		box-shadow: 0 1px 3px rgb(0 0 0 / 6%), 0 1px 2px rgb(0 0 0 / 12%);
		z-index: 10;
	}
	header .row{
		height: 100%;
	}
	a:hover{
		text-decoration: none;
	}
	#pageTitle{
		color: var(--offBlack);
		margin: 0;
		font-size: 22px;
		line-height: 1.75;
		font-weight: 500;
	}
</style>

<header>
	<div class="row">
		<a href="../" sveltekit:prefetch>
			<h1 id="pageTitle">Parshandata</h1>
		</a>
		<Button text="" style="secondary" on:click={()=>alert('testing')} />
	</div>
</header>