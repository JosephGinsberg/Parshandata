<script lang="ts">
	import SearchOption from './_SearchOption.svelte'
	import Button from '$lib/Button.svelte'

	export let search: searchRequest | JSON | any, updateSearch: Function, runSearch: VoidFunction

	let isDevMode: boolean = true,
		lastClicks: string[] = [],
		request: string = JSON.stringify(search, undefined, 4),
		update: boolean = true,
		fileSelector: HTMLInputElement

	const changeToDEV = (e: KeyboardEvent): void => {
			lastClicks.push(e.key)
			if (lastClicks.length > 3) lastClicks.shift()
			if (lastClicks.join('') === 'dev') isDevMode = !isDevMode
		},
		validateJson = (expectedJson: string): boolean => {
			try {
				JSON.parse(expectedJson)
				return true
			} catch {
				return false
			}
		},
		uploadSearch = async (): Promise<void> => {
			if (!fileSelector.files) return

			const savedSearch = await fileSelector.files[0].text()
			search = JSON.parse(savedSearch)
		},
		downloadSearch = (): void => {
			let dataString: string = JSON.stringify(JSON.parse(request), undefined, 4),
				filename: string = 'Search | Parshandata.json'

			// initiate download
			let universalBOM: string = '\uFEFF',
				link: HTMLElement = document.createElement('a')
			link.setAttribute(
				'href',
				'data:text/csv;charset=utf-8,' + encodeURIComponent(universalBOM + dataString)
			)
			link.setAttribute('download', filename)
			link.click()
		}

	// $: if(validateJson(request) && !update) search = JSON.parse(request)
	$: if (validateJson(request)) updateSearch(JSON.parse(request))
	$: if (request !== search && update && validateJson(request))
		request = JSON.stringify(search, undefined, 4)
</script>

<div class="container" on:keyup={changeToDEV}>
	{#if !isDevMode}
		<!-- <div style="position: absolute;top: var(--topPadding);right: var(--topPadding);">
			<Button classes='minimal small' icon='library_add' text='Add element' />
		</div> -->

		<div class="optionsContainer">
			<div>Display <span style="display: none;">Search</span> every {search.splitBy}</div>

			{#each search.search as element, i (element)}
				<SearchOption {element} index={i} />
			{/each}
		</div>
	{:else}
		<textarea
			class:error={!validateJson(request)}
			bind:value={request}
			on:focus={() => (update = !update)}
			on:blur={() => (update = !update)}
			autocomplete="off"
			spellcheck="false"
		/>
	{/if}

	<div class="buttonsContainer row">
		<Button style="default" text="Search" on:click={runSearch} />
		<div>
			<Button classes="minimal small" icon="save" on:click={downloadSearch} />
			<Button
				classes="minimal small"
				icon="upload"
				text="Upload"
				on:click={() => fileSelector.click()}
			/>
			<input type="file" bind:this={fileSelector} on:change={uploadSearch} accept=".json" />
		</div>
	</div>
</div>

<style>
	.container {
		display: flex;
		flex-direction: column;
		height: 100%;
		max-height: 100%;
		width: calc(50% - var(--sidePadding));
		margin-inline-end: var(--sidePadding);
		padding-right: 0;
		box-sizing: border-box;
		/* overflow-y: auto; */
		background-color: var(--defaultBackground);
		border: 2px solid var(--lightText);
		border-radius: var(--borderRadius);
	}
	.container:focus-within,
	.container:focus-within .buttonsContainer {
		border-color: var(--supportText);
	}
	.container .optionsContainer {
		padding: calc(var(--topPadding) / 1.5) var(--topPadding);
		height: 100%;
	}
	textarea {
		width: 100%;
		height: 100%;
		background-color: transparent;
		border: none;
		outline: none;
		resize: none;
	}
	textarea.error {
		color: red;
	}
	.buttonsContainer {
		align-items: center;
		padding: calc(var(--topPadding) / 1.5) var(--topPadding);
		border-top: 2px solid var(--lightText);
	}
	input[type='file'] {
		display: none;
	}
</style>
