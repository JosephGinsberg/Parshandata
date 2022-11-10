<script>
	import Button from '$lib/Button.svelte'
</script>

<svelte:head>
	<title>About | Parshandata</title>
	<meta
		name="description"
		content="About the parshandata project and how we are inovating on Tanakh search."
	/>
</svelte:head>

<section>
	<h1>About the project</h1>

	<h3>What is Parshandata?</h3>
	<p>
		Parshandata is an advanced search engine for Tanakh that was created by Yosef Ginsberg (engine)
		and Nosson Frankel (interface).
	</p>

	<h3>Why is Parshandata different?</h3>
	<p>
		Tools for searching Tanakh in–depth do exist, but they are ubiquitously clunky or pricey. The
		core idea of Parshandata is an intuitive, simple – and free – interface that affords scholar and
		student alike the ability to search Tanakh with ease. Additionally, Parshandata allows one to
		layer and interconnect different searches, giving them a strong and flexible means of canvassing
		the entire Tanakh for specific linguistic phenomena.
	</p>
	<p>In a similar vein, we hope Parshandata will:</p>
	<ul>
		<li>
			Streamline research on Hebrew accentuation (<i>Ta'amei HaMikra</i>, or Trop). The system that
			governs accentuation in Tanakh is complex and opaque, and very often certain formations have
			only been partially understood. This is especially true regarding the poetic accents (<i
				>Ta'amei Emes</i
			>) which are used only in the non–prosaic books of Psalms, Proverbs, and Job. Parshandata, as
			a fine–toothed research tool, allows for proper and critical analysis of such accentuation.
		</li>
		<li>
			Facilitate investigation into the Masorah. The Masorah is a collection of comments and glosses
			(mostly found in manuscript) that were compiled by the Masoretes, ostensibly for the purpose
			of preserving the text of Tanakh as accurately as possible. They often pertain to linguistic
			minutiae, and are nearly always short and cryptic. For example, a typical note will state that
			a certain phenomena only appears two times in the entire Tanakh, which can be quite tedious to
			verify. Parshandata makes checking the accuracy of such Masoretic notes a clean and quick
			experience.
		</li>
	</ul>
	<p>
		A relatively simple example: the Masorah states that the word "כֹּה" with the <i>legarmeh</i>
		accent ("כֹּ֣ה ׀") appears 18 times in the book of Jeremiah (this note has been misinterpreted several
		times by different scholars). But what exactly are these 18 occurrences? Scholars have struggled
		to identify them for one reason or another. The popular Biblia Hebraica Stuttgartensia (BHS) fails
		to note an accurate list, and C. D. Ginsburg in “The Massorah” attempts to list 18 places, but one
		of them is erroneous (he mistakenly lists 33:4 instead of 47:2).
	</p>
	<p>
		<a href="/Jeremiah_Koh_with_legarmeh.json" download sveltekit:prefetch>This</a> simple search, when
		uploaded on Parshandata, easily reveals all 18 occurrences.
	</p>
	<p>
		Parshandata uses the open–source text provided by the Westminster Leningrad Codex (WLC) project,
		which is based on the Leningrad Codex, the oldest complete Tanakh manuscript extant. Though the
		text has slight idiosyncrasies (which we have tried to mitigate), as it is based entirely on a
		single codex, it is highly accurate and is generally the standard critical text of Tanakh used
		on the web.
	</p>

	<h3>What else can one do with Parshandata?</h3>
	<p>
		Parshandata allows searches to be saved locally and re–uploaded later, and for search results to
		be downloaded in a convenient spreadsheet. In addition, both dark/light aesthetics and
		English/Hebrew compatibility have been integrated into the site.
	</p>

	<h3>What is the future of Parshandata?</h3>
	<p>
		We hope to continue to expand this project to include other Tanakh–based research tools, and
		constantly update and improve the current apparatus. To facilitate this, if you find Parshandata
		particularly helpful or inspiring, please consider expressing your appreciation by donating to
		the project!
	</p>

	<Button icon="coffee" text="Donate" href="https://www.buymeacoffee.com/parshandata" />
</section>

<style>
	section {
		padding–bottom: 8rem;
	}
	h1 {
		margin–bottom: 2.5rem;
		padding–bottom: 1rem;
		border–bottom: 0.25rem solid var(––primary–txt–color);
	}
	li + li {
		padding–top: 1rem;
	}
</style>
