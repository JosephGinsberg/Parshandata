<script lang="ts">
	import Button from '$lib/Button.svelte'
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
	<Button text="Start searching" href="/search" />
</section>

<section>
	<div class="header">
		<h3>Frequently asked questions</h3>
		<h5>Get answers to common questions about Parshandata.</h5>
	</div>

	<label class="question" for="zero">What is Parshandata?</label>
	<input type="checkbox" id="zero" />
	<div class="answer">
		Parshandata is an advanced search engine for Tanakh that was created by Yosef Ginsberg (engine)
		and Nosson Frankel (interface).
	</div>

	<label class="question" for="one"
		>How is this software different from others that offer search?</label
	>
	<input type="checkbox" id="one" />
	<div class="answer">
		On other platforms one can search for words, phrases, books, and all of them combined. On our
		platform, we are expanding the level of search one can perform. One can now search for pasukim
		or words based on a pattern. The most suitable analogy is that our tool delivers the power of a
		regex with the UI/UX of Google search. <a href="/about">Learn more</a>
	</div>

	<label class="question" for="two">What is the future of Parshandata?</label>
	<input type="checkbox" id="two" />
	<div class="answer">
		We hope to continue to expand this project to include other Tanakh-based research tools, and
		constantly update and improve the current apparatus. To facilitate this, if you find Parshandata
		particularly helpful or inspiring, please consider expressing your appreciation by donating to
		the project!
	</div>
</section>
<br /><br /><br />

<style>
	section {
		--topPadding: 4rem;
	}
	#hero h1 {
		margin: 80px 0 40px;
	}
	#hero h4 {
		margin: 60px 0;
	}

	#hero h1,
	#hero h4 {
		max-width: 600px;
	}
	.header {
		padding-bottom: 2rem;
	}
	.header::before {
		display: block;
		background: var(--gray-shade-2);
		height: 4px;
		width: 160px;
		content: '';
		margin-bottom: 4rem;
	}
	.header h3,
	.header h5 {
		margin-top: 0px;
	}
	input[type='checkbox'] {
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
	input[type='checkbox']:checked + .answer {
		max-height: unset;
		padding-bottom: 1rem;
		border-bottom: 1px solid var(--gray-shade-2);
	}
</style>
