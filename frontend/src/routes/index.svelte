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
		}
	]
</script>

<svelte:head>
	<title>Home | Parshandata</title>
	<meta name="description" content="Have the ability to search Tanach with great control" />
</svelte:head>

<section id="hero">
	<h1>Ready to start searching?</h1>
	<h4>
		An intuitive, simple - and free - interface that affords scholar and student alike the ability
		to search Tanakh with ease.
	</h4>
	<Button style="margin-top: 1.5rem;" text="Start searching" href="/search" />
</section>

<section>
	<div class="header">
		<h3>Frequently asked questions</h3>
		<h5>Get answers to common questions about Parshandata.</h5>
	</div>

	<label class="question" for="zero">What is Parshandata?</label>
	<input type="checkbox" id="zero">
	<div class="answer">Parshandata is an advanced search engine for Tanakh that was created by Yosef Ginsberg (engine) and Nosson Frankel (interface).</div>

	<label class="question" for="one">How is this software different from others that offer search?</label>
	<input type="checkbox" id="one">
	<div class="answer">On other platforms one can search for words, phrases, books, and all of them combined. On our platform, we are expanding the level of search one can perform. One can now search for pasukim or words based on a pattern. The most suitable analogy is that our tool delivers the power of a regex with the UI/UX of Google search. <a href="/about">Learn more</a></div>

	<label class="question" for="two">What is the future of Parshandata?</label>
	<input type="checkbox" id="two">
	<div class="answer">We hope to continue to expand this project to include other Tanakh-based research tools, and constantly update and improve the current apparatus. To facilitate this, if you find Parshandata particularly helpful or inspiring, please consider expressing your appreciation by donating to the project!</div>
</section>
<br><br><br>

<!-- <section>
	<h3>
		OUR CURRENT PLAN.
		<span> LOOKING INTO THE FUTURE </span>
	</h3>
	<p>
		<b>WRITE SOMETHING HERE.</b> We are a small team with not a lot of time. Therefore, we are prioritizing
		out time to bring the tools that improve the market the most first.
	</p>
</section>

<section>
	<h3>
		THE BASICS.
		<span> AND EVERYTHING IN BETWEEN </span>
	</h3>

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
	<h3 style="margin: 0 auto;">our mission</h3>
	<p>"Our goal is to break the barrier for an average Shloimy to be able to search with minimal know-how."</p>
</section> -->
<style>
	section#hero h1,
	section#hero h4 {
		margin-top: 0;
		max-width: 600px;
	}
	.header {
		padding-bottom: 2rem;
	}
	.header::before {
		display: block;
		background: #0052cc;
		background: var(--gray-shade-2);
		height: 4px;
		width: 160px;
		content: '';
		margin-bottom: 4rem;
	}
	.header h3, .header h5{
		margin-top: 0px;
	}
	input[type=checkbox] {
		display: none;
	}
	.question {
		padding-top: 1rem;
		font-size: 1.333rem;
		font-weight: 700;
		user-select: none;
	}
	.answer {
		margin: 1rem 0 0;
		max-height: 0px;
		overflow: hidden;
		/* transition: max-height ease-in-out 200ms; */
	}
	input[type=checkbox]:checked + .answer {
		max-height: unset;
		padding-bottom: 1rem;
		border-bottom: 1px solid var(--gray-shade-2);
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
