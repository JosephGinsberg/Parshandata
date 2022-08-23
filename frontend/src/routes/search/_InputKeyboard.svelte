<script lang="ts">
	import { globalState } from '../../globalState'
	export let group = 'trop',
		value = 'Select',
		index: number

	const updateValue = (value: string) => {
			$globalState.searchRequest.search[index].type = active
			$globalState.searchRequest.search[index].value = value
		},
		inputList: any = {
			letter: [
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
				{ value: 'ל', english: 'lamed', hebrew: 'Hebrew' },
				{ value: 'מ', english: 'mem', hebrew: 'Hebrew' },
				{ value: 'נ', english: 'nun', hebrew: 'Hebrew' },
				{ value: 'ס', english: 'samach', hebrew: 'Hebrew' },
				{ value: 'ע', english: 'ayin', hebrew: 'Hebrew' },
				{ value: 'פ', english: 'pei', hebrew: 'Hebrew' },
				{ value: 'צ', english: 'tsadi', hebrew: 'Hebrew' },
				{ value: 'ק', english: 'kuf', hebrew: 'Hebrew' },
				{ value: 'ר', english: 'reish', hebrew: 'Hebrew' },
				{ value: 'שׂ', english: 'sin', hebrew: 'Hebrew' },
				{ value: 'שׁ', english: 'shin', hebrew: 'Hebrew' },
				{ value: 'ת', english: 'tav', hebrew: 'Hebrew' },
				{ value: 'ם', english: 'chaf-sofit', hebrew: 'Hebrew' },
				{ value: 'ן', english: 'mem-sofit', hebrew: 'Hebrew' },
				{ value: 'ץ', english: 'nun-sofit', hebrew: 'Hebrew' },
				{ value: 'ף', english: 'pei-sofit', hebrew: 'Hebrew' },
				{ value: 'ך', english: 'tsadi-sofit', hebrew: 'Hebrew' }
			],
			nekudah: [
				{ value: 'ָ', english: 'kamatz', hebrew: 'Hebrew' },
				{ value: 'ַ', english: 'patach', hebrew: 'Hebrew' },
				{ value: 'ֵ', english: 'tsaray', hebrew: 'Hebrew' },
				{ value: 'ֶ', english: 'segol', hebrew: 'Hebrew' },
				{ value: 'ֹ', english: 'cholam', hebrew: 'Hebrew' },
				{ value: 'ֻ', english: 'shuruk', hebrew: 'Hebrew' },
				{ value: 'ִ', english: 'chirik', hebrew: 'Hebrew' },
				{ value: 'ְ', english: 'shva', hebrew: 'Hebrew' },
				{ value: 'ֺ', english: 'cholam chaser for vav', hebrew: 'Hebrew' },
				{ value: 'ֳ', english: 'chataf-kamatz', hebrew: 'Hebrew' },
				{ value: 'ֲ', english: 'chataf-patach', hebrew: 'Hebrew' },
				{ value: 'ֱ', english: 'chataf-segol', hebrew: 'Hebrew' }
			],
			trop: [
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
			value: []
		}

	let showKeyboard = false,
		active = group
</script>

<div class="container">
	<div class="currentInput" on:click={() => (showKeyboard = true)}>{value}</div>
	{#if showKeyboard}
		<div class="container card">
			<div class="tabContainer row">
				{#each Object.keys(inputList) as group}
					<span class="tab" class:active={active === group} on:click={() => (active = group)}>
						{group}
					</span>
				{/each}
			</div>
			<div class="values row">
				{#if active !== 'value'}
					{#each inputList[active] as input}
						<div
							class="value"
							class:selected={input.value === value || input.english === value}
							on:click={() => updateValue(active === 'letter' ? input.value : input.english)}
						>
							{input.english.replaceAll('-', ' ')}
						</div>
					{/each}
				{:else}
					show text input
				{/if}
			</div>
		</div>
	{/if}
</div>

<style>
	.container {
		/* position: relative; */
		position: static;
		display: inline-block;
	}
	.currentInput {
		display: inline-block;
		width: 50px;
		height: 1.75em;
		line-height: 1.75em;
		margin-left: 8px;
		margin-block-end: 0.5rem;
		padding: 0 0.5rem;
		font-size: 0.875rem;
		box-sizing: content-box;
		background-color: var(--secondary-bg-color);
		border: 2px solid var(--gray-shade-2);
		border-radius: var(--borderRadius);
		cursor: pointer;
		user-select: none;
	}
	/* temp: start */
	.container.card {
		position: absolute;
		top: 98%;
		left: 0px;
		/* left: 100px; */
		z-index: 25;
		/* margin: 2rem auto 3rem; */
	}
	/* temp: end */
	.container.card {
		max-width: 400px;
		background-color: var(--primary-bg-color);
	}
	.tabContainer.row {
		justify-content: flex-start;
		padding-bottom: 0.5rem;
		margin-bottom: 0.75rem;
		border-bottom: 2px solid var(--gray-shade-1);
	}
	.values.row {
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
	.values.row {
		flex-wrap: wrap;
	}
	.values.row .value {
		background-color: var(--tertiary-bg-color);
		border-radius: var(--borderRadius);
		border: 2px solid transparent;
		padding: 0.25rem 0.5rem;
		text-transform: capitalize;
		cursor: pointer;

		margin-inline-end: 0.5rem;
		margin-block-end: 0.5rem;
	}
	.values.row .value.selected,
	.values.row .value:active {
		background-color: var(--quaternary-bg-color);
		background-color: var(--primary-bg-color);
		border-color: var(--gray-shade-2);
	}
	.values.row .value:not(:active):hover {
		background-color: var(--gray-shade-2);
	}
</style>
