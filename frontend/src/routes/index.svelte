<script lang="ts">
	import Button from '$lib/Button.svelte'

	interface Example {
		title: string
		desc: string
		location: string
	}

	const examples: Example[] = [
		{
			title: 'Search Tanach',
			desc: 'With advanced functionality', // any search is possible",
			location: '/search'
		} //,
		// {
		// 	title: 'Gemtria calulator',
		// 	desc: 'Multiple methods at once',
		// 	location: 'movable' //"/gematria"
		// }
	]
</script>

<svelte:head>
	<title>Home | Parshandata</title>
	<meta name="description" content="Have the ability to search Tanach with great control" />
</svelte:head>

<section id="hero">
	<h1>Ready to start searching?</h1>
	<h3>Have greater access to <i>Tanach</i> with our easier tooling at your finger tips.</h3>
	<Button style="margin-top: 1.5rem;" text="Check out our tooling" href="/search" />
</section>
<br /><br /><br />

<!-- <section>
	<h2>
		OUR CURRENT PLAN.
		<span> LOOKING INTO THE FUTURE </span>
	</h2>
	<p>
		<b>WRITE SOMETHING HERE.</b> We are a small team with not a lot of time. Therefore, we are prioritizing
		out time to bring the tools that improve the market the most first.
	</p>
</section>

<section>
	<h2>
		THE BASICS.
		<span> AND EVERYTHING IN BETWEEN </span>
	</h2>

	<div class="row spacedContainer">
		{#each examples as example}
			<div class="card" on:click={() => (window.location.href = `${example.location}`)}>
				<div>
					<div class="icon">
						<svg
							stroke="currentColor"
							fill="currentColor"
							stroke-width="0"
							viewBox="0 0 20 20"
							height="1em"
							width="1em"
							xmlns="http://www.w3.org/2000/svg"
							><path
								fill-rule="evenodd"
								d="M2 5a2 2 0 012-2h12a2 2 0 012 2v10a2 2 0 01-2 2H4a2 2 0 01-2-2V5zm3.293 1.293a1 1 0 011.414 0l3 3a1 1 0 010 1.414l-3 3a1 1 0 01-1.414-1.414L7.586 10 5.293 7.707a1 1 0 010-1.414zM11 12a1 1 0 100 2h3a1 1 0 100-2h-3z"
								clip-rule="evenodd"
							/></svg
						>
					</div>
					<div>
						<div class="title bodyLarge bold">{example.title}</div>
						<div class="desc bodySmall">{example.desc}</div>
					</div>
				</div>
			</div>
		{/each}
	</div>
</section>

<section style="text-align: center;">
	<h2 style="margin: 0 auto;">our mission</h2>
	<p>"Our goal is to break the barrier for an average Shloimy to be able to search with minimal know-how."</p>
</section> -->
<style>
	section#hero {
		padding-bottom: calc(75px + var(--sidePadding));
		margin-bottom: var(--sidePadding);
		border-bottom: 2px solid #000;
	}
	section#hero h1,
	section#hero h3 {
		max-width: 600px;
	}
	/* section .row {
		justify-content: left;
	}
	.card {
		width: 200px;
		margin-right: 3rem;
		cursor: pointer;
	}
	.card .icon {
		display: flex;
		align-items: center;
		justify-content: center;
		height: 25px;
		width: 25px;
		padding-right: 1rem;
		padding-bottom: 1rem;
	}
	.card .title {
		font-weight: 700;
		line-height: 24px;

		color: var(--gray-900);
		margin-bottom: 2px;
		margin-top: 3px;
		transition: color 0.15s;
	}
	.card .desc {
		font-size: 14px;
		font-weight: 400;
		line-height: 20px;

		color: var(--gray-600);
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
	} */
</style>
