<script lang="ts">
	import { search, general } from '$lib/appState';
	import Button from '$lib/ui/Button.svelte';
	import SearchParam from './SearchParam.svelte';

	export let runSearch: any;

	let fileSelector: HTMLInputElement,
		manualRequest = JSON.stringify($search, undefined, 4),
		editMode = false;
	$: if (!editMode) manualRequest = JSON.stringify($search, undefined, 4);

	const trySettingSearch = (searchAttempt: string) => {
		try {
			$search = JSON.parse(searchAttempt);
		} catch {}
	};
	const uploadSearch = async (): Promise<void> => {
		if (!fileSelector.files?.[0]) return;

		const savedSearch = await fileSelector.files[0].text(),
			formattedFile = trySettingSearch(savedSearch);
		// $search = { ...$search, ...formattedFile }
	};
	const saveSearch = (): void => {
		let dataString: string = JSON.stringify($search, undefined, 4),
			filename: string = 'Search | Parshandata.json';

		// initiate download
		let universalBOM: string = '\uFEFF',
			link: HTMLElement = document.createElement('a');
		link.setAttribute(
			'href',
			'data:text/plain;charset=utf-8,' + encodeURIComponent(universalBOM + dataString)
		);
		link.setAttribute('download', filename);
		link.click();
	};
</script>

<div
	class="container"
	on:contextmenu|preventDefault={() => ($general.isDevMode = !$general.isDevMode)}
>
	{#if !$general.isDevMode}
		<div class="optionsContainer" style="padding-top: calc(var(--topPadding) / 2);overflow: auto;">
			
			{#each $search.search as element, i (element)}
				<SearchParam {element} index={i} />
			{/each}
		</div>
	{:else}
		<!-- class:error={!validateJson(request)} -->
		<textarea
			autocomplete="off"
			spellcheck="false"
			bind:value={manualRequest}
			on:keyup={() => trySettingSearch(manualRequest)}
			on:focus={() => (editMode = true)}
			on:blur={() => (editMode = false)}
		/>
	{/if}

	<div class="buttonsContainer row">
		<Button style="default" text="Search" on:click={runSearch} />
		<div>
			<Button classes="minimal small" icon="save" on:click={saveSearch} />
			<Button
				classes="minimal small"
				icon="upload"
				text="Upload"
				on:click={() => fileSelector.click()}
			/>
			<input type="file" accept=".json" bind:this={fileSelector} on:change={uploadSearch} />
		</div>
	</div>
</div>

<style>
	.container {
		display: flex;
		flex-direction: column;
		height: 100%;
		background-color: var(--primary-bg-color);
		border: 2px solid var(--gray-shade-2);
		border-radius: var(--borderRadius);
	}
	.container .optionsContainer {
		height: 100%;
	}
	textarea {
		width: 100%;
		height: 100%;
		padding: calc(var(--topPadding) / 2);
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
