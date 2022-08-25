<script lang="ts">
	import { globalState } from '../../globalState'
	import { clickedOutside } from '$lib/clickedOutside'
	export let index: number

	let showKeyboard = false,
		inputEl: any,
		active = 'letters'

	const inputList: any = {
			letters: [
				{ value: 'א', english: 'aleph', hebrew: 'Hebrew' },
				{ value: 'ב', english: 'beis', hebrew: 'Hebrew' },
				{ value: 'ג', english: 'gimmel', hebrew: 'Hebrew' },
				{ value: 'ד', english: 'dalet', hebrew: 'Hebrew' },
				{ value: 'ה', english: 'hei', hebrew: 'Hebrew' },
				{ value: 'ו', english: 'vav', hebrew: 'Hebrew' },
				{ value: 'ז', english: 'zayin', hebrew: 'Hebrew' },
				{ value: 'ח', english: 'ches', hebrew: 'Hebrew' },
				{ value: 'ט', english: 'tes', hebrew: 'Hebrew' },
				{ value: 'י', english: 'yud', hebrew: 'Hebrew' },
				{ value: 'כ', english: 'chaf', hebrew: 'Hebrew' },
				{ value: 'ך', english: 'chaf-sofit', hebrew: 'Hebrew' },
				{ value: 'ל', english: 'lamed', hebrew: 'Hebrew' },
				{ value: 'מ', english: 'mem', hebrew: 'Hebrew' },
				{ value: 'ם', english: 'mem-sofit', hebrew: 'Hebrew' },
				{ value: 'נ', english: 'nun', hebrew: 'Hebrew' },
				{ value: 'ן', english: 'nun-sofit', hebrew: 'Hebrew' },
				{ value: 'ס', english: 'samach', hebrew: 'Hebrew' },
				{ value: 'ע', english: 'ayin', hebrew: 'Hebrew' },
				{ value: 'פ', english: 'pei', hebrew: 'Hebrew' },
				{ value: 'ף', english: 'pei-sofit', hebrew: 'Hebrew' },
				{ value: 'צ', english: 'tsadi', hebrew: 'Hebrew' },
				{ value: 'ץ', english: 'tsadi-sofit', hebrew: 'Hebrew' },
				{ value: 'ק', english: 'kuf', hebrew: 'Hebrew' },
				{ value: 'ר', english: 'reish', hebrew: 'Hebrew' },
				{ value: 'שׂ', english: 'sin', hebrew: 'Hebrew' },
				{ value: 'שׁ', english: 'shin', hebrew: 'Hebrew' },
				{ value: 'ת', english: 'tav', hebrew: 'Hebrew' }
			],
			nekudot: [
				{ value: 'ָ', english: 'kamatz', hebrew: 'Hebrew' },
				{ value: 'ַ', english: 'patach', hebrew: 'Hebrew' },
				{ value: 'ֵ', english: 'tsaray', hebrew: 'Hebrew' },
				{ value: 'ֶ', english: 'segol', hebrew: 'Hebrew' },
				{ value: 'ֹ', english: 'cholam', hebrew: 'Hebrew' },
				{ value: 'ֻ', english: 'shuruk', hebrew: 'Hebrew' },
				{ value: 'ִ', english: 'chirik', hebrew: 'Hebrew' },
				{ value: 'ְ', english: 'shva', hebrew: 'Hebrew' },
				{ value: 'ֳ', english: 'chataf-kamatz', hebrew: 'Hebrew' },
				{ value: 'ֲ', english: 'chataf-patach', hebrew: 'Hebrew' },
				{ value: 'ֱ', english: 'chataf-segol', hebrew: 'Hebrew' },
				{ value: 'ֺ', english: 'cholam chaser for vav', hebrew: 'Hebrew' }
			],
			ta_amim: [
				{ value: '֨', english: 'kadmah', hebrew: 'Hebrew' },
				{ value: '֣', english: 'munach', hebrew: 'Hebrew' },
				{ value: '֮', english: 'zarka', hebrew: 'Hebrew' },
				{ value: '֒', english: 'segol', hebrew: 'Hebrew' },
				{ value: '֗', english: 'revie', hebrew: 'Hebrew' },
				{ value: '֤', english: 'mahpach', hebrew: 'Hebrew' },
				{ value: '֙', english: 'pashtah', hebrew: 'Hebrew' },
				{ value: '֔', english: 'zakaif', hebrew: 'Hebrew' },
				{ value: '֕', english: 'zakaif-gadol', hebrew: 'Hebrew' },
				{ value: '֥', english: 'mercha', hebrew: 'Hebrew' },
				{ value: '֖', english: 'tipcha', hebrew: 'Hebrew' },
				{ value: '֑', english: 'esnachta', hebrew: 'Hebrew' },
				{ value: '֡', english: 'pazair', hebrew: 'Hebrew' },
				{ value: '֩', english: 'telisha-ketanah', hebrew: 'Hebrew' },
				{ value: '֠', english: 'telisha-gedolah', hebrew: 'Hebrew' },
				{ value: '֜', english: 'azlah-geiresh', hebrew: 'Hebrew' },
				{ value: '֞', english: 'gershayim', hebrew: 'Hebrew' },
				{ value: '֧', english: 'dargah', hebrew: 'Hebrew' },
				{ value: '֛', english: 'tevier', hebrew: 'Hebrew' },
				{ value: '֚', english: 'yesiv', hebrew: 'Hebrew' },
				{ value: '֓', english: 'shalsheles', hebrew: 'Hebrew' },
				{ value: '֪', english: 'yareach-ben-yomo', hebrew: 'Hebrew' },
				{ value: '֟', english: 'karnei-pharah', hebrew: 'Hebrew' },
				{ value: '֦', english: 'mercha-kefula', hebrew: 'Hebrew' },
				{ value: '׃', english: 'sof-pasuk', hebrew: 'Hebrew' },
				{ value: '֭', english: 'dechi', hebrew: 'Hebrew' },
				{ value: '֘', english: 'tsinor', hebrew: 'Hebrew' },
				{ value: '֬', english: 'elui', hebrew: 'Hebrew' },
				{ value: '֫', english: 'oleh', hebrew: 'Hebrew' },
				{ value: '֝', english: 'mugrash', hebrew: 'Hebrew' },
				{ value: '׀', english: 'pesik', hebrew: 'Hebrew' }
			],
			others: [
				{ value: 'ּ', english: 'dagesh', hebrew: 'Hebrew' },
				{ value: 'ֿ', english: 'rafeh', hebrew: 'Hebrew' },
				{ value: 'ׄ', english: 'top dots', hebrew: 'Hebrew' },
				{ value: 'ׅ', english: 'bottom dots', hebrew: 'Hebrew' },
				{ value: 'ֽ', english: 'gaya', hebrew: 'Hebrew' },
				{ value: '־', english: 'makaf', hebrew: 'Hebrew' }
			]
		},
		updateValue = (value: string) => {
			let { selectionStart, selectionEnd } = inputEl,
				startSection = $globalState.searchRequest.search[index].value?.slice(0, selectionStart),
				endSection = $globalState.searchRequest.search[index].value?.slice(selectionEnd),
				newValue = startSection + value + endSection

			$globalState.searchRequest.search[index].value = newValue
			$globalState.searchRequest.search[index].type = newValue.length === 1 ? active : 'value'

			setTimeout(() => {
				inputEl.selectionStart = selectionStart + 1
				inputEl.focus()
			}, 10)
		}
</script>

<div class="container main">
	<input
		type="text"
		class="small rtl"
		style="width: 100px;"
		placeholder="Select a char"
		bind:this={inputEl}
		bind:value={$globalState.searchRequest.search[index].value}
		on:click={() => (showKeyboard = true)}
	/>

	{#if showKeyboard || true}
		<div class="container card" use:clickedOutside on:outclick={() => (showKeyboard = false)}>
			<div class="tabContainer row">
				{#each Object.keys(inputList) as group}
					<span class="tab" class:active={active === group} on:click={() => (active = group)}>
						{group.replaceAll('_', "'")}
					</span>
				{/each}
			</div>
			<div class="chars row" class:rtl={active === 'letters'}>
				{#each inputList[active] as input}
					<div
						class="char"
						on:click={() => updateValue(active !== 'ta_amim' ? input.value : input.english)}
					>
						<div class="value">&nbsp;&#8203;{input.value} &#8203;&nbsp;</div>
						<div class="name subtext">{input.english}</div>
					</div>
				{/each}
			</div>
		</div>
	{/if}
</div>

<style>
	.container.main {
		position: static;
		display: inline-block;
	}
	.container:active input {
		border: solid 2px var(--gray-shade-6);
	}
	.container.card {
		position: absolute;
		top: 98%;
		top: 200%;
		left: 0px;
		left: 75px;
		max-width: 400px;
		background-color: var(--primary-bg-color);
		z-index: 25;
	}
	.tabContainer.row {
		justify-content: flex-start;
		padding-bottom: 0.5rem;
		margin-bottom: 0.25rem;
		border-bottom: 2px solid var(--gray-shade-1);
	}
	.chars.row {
		justify-content: flex-start;
	}
	.tab {
		margin-inline-end: 1rem;
		text-transform: capitalize;
		cursor: pointer;
	}
	.tab::after {
		position: relative;
		top: 3px;
		display: block;
		width: 100%;
		height: 2px;
		background-color: transparent;
		border-radius: 2px;
		content: '';
	}
	.tab:hover::after {
		background-color: var(--blue-primary);
	}
	.tab.active::after {
		background-color: var(--blue-dark);
	}
	.chars.row {
		flex-wrap: wrap;
		justify-content: space-around;
	}
	.chars .char {
		min-width: 2.75rem;
		margin-inline-end: 0.5rem;
		margin-block-start: 0.5rem;
		padding: 0.25rem 0.5rem;
		background-color: var(--secondary-bg-color);
		border-radius: var(--borderRadius);
		border: 2px solid var(--gray-shade-1);
		text-align: center;
		cursor: pointer;
	}
	.chars .char ::first-letter {
		text-transform: capitalize;
	}
	.chars .char:hover {
		background-color: var(--tertiary-bg-color);
		border-color: var(--gray-shade-2);
	}
	.chars .char:active {
		background-color: var(--secondary-bg-color);
		border-color: var(--gray-shade-1);
	}
	.chars .char .value {
		font: 700 1.25rem var(--hebrew-font);
	}
	.chars .char .name {
		padding-top: 0.25rem;
	}
</style>
