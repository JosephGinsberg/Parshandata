<script lang="ts">
	import { globalState } from '../../globalState'
	import SearchOption from './_SearchOption.svelte'
	import Button from '$lib/Button.svelte'

	export let runSearch: any

	let isDevMode = true,
		request = JSON.stringify($globalState.searchRequest, undefined, 4),
		update = true,
		fileSelector: HTMLInputElement

	const validateJson = (expectedJson: string): boolean => {
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
			$globalState.searchRequest = JSON.parse(savedSearch)
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
		},
		addBlock = (): void => {
			$globalState.searchRequest.search[$globalState.searchRequest.search.length - 1].connector =
				'or'
			const tempBlock: SearchParam = {
				param: 'input',
				type: 'letter',
				value: '',
				matchtype: 'contains',
				count: 1,
				counttype: 'equal',
				connector: 'none',
				level: 1
			}

			$globalState.searchRequest.search = [...$globalState.searchRequest.search, tempBlock]
		},
		updateSearch = (): void => {
			if (validateJson(request)) $globalState.searchRequest = JSON.parse(request)
		}

	$: if (update) request = JSON.stringify($globalState.searchRequest, undefined, 4)
</script>

<div class="container" on:contextmenu|preventDefault={() => (isDevMode = !isDevMode)}>
	{#if isDevMode}
		<div
			class="row"
			style="position: absolute;top: calc(var(--topPadding) / 1.5);right: var(--topPadding);flex-direction: column;"
		>
			<Button
				classes="minimal small"
				style="width: 100%;margin-bottom: 6px;"
				icon="library_add"
				text="Add block"
				on:click={addBlock}
			/>
		</div>

		<div class="optionsContainer" style="padding-top: var(--topPadding);overflow: auto;">
			<div>
				Return every {$globalState.searchRequest.splitBy}
				<!-- <select
					class="small"
					style="display: inline-block;width: 125px;margin-inline-start: .5rem;"
					placeholder={$globalState.searchRequest.splitBy}
					bind:value={$globalState.searchRequest.splitBy}
				>
					<option value="pasuk">Pasuk</option>
					<option value="word">Word</option>
					<option value="tropword">Tropword</option>
				</select> -->
			</div>

			{#each $globalState.searchRequest.search as element, i (element)}
				<SearchOption {element} index={i} />
			{/each}
		</div>
	{:else}
		<textarea
			class:error={!validateJson(request)}
			on:keyup={updateSearch}
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
		max-width: calc(42.5% - var(--sidePadding)); /** TEMP FIX **/
		margin-inline-end: var(--sidePadding);
		padding-right: 0;
		box-sizing: border-box;
		background-color: var(--primary-bg-color);
		border: 2px solid var(--gray-shade-2);
		border-radius: var(--borderRadius);
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
		border-top: 2px solid var(--gray-shade-2);
	}
	input[type='file'] {
		display: none;
	}
</style>
