<script lang="ts">
	import { globalState } from '../../globalState'
	import { clickedOutside } from '$lib/clickedOutside'
	import Menu from '$lib/Menu.svelte'
	import InputKeyboard from './_InputKeyboard.svelte'

	export let element: SearchParam, index: number

	let editMode = false

	const deleteBlock = () =>
		($globalState.searchRequest.search = $globalState.searchRequest.search.filter(
			block => block != element
		))
</script>

<div
	class="option"
	class:editMode
	on:click={() => (editMode = true)}
	use:clickedOutside
	on:outclick={() => (editMode = false)}
>
	{#if element.param === 'input' && !editMode}
		{element.matchtype}

		{#if element.matchtype === 'is' || element.matchtype === 'is not'}
			a
		{:else if element.counttype === 'greater' && element.count === 0}
			any count of a
		{:else if element.counttype === 'greater'}
			greater than {element.count}
		{:else if element.counttype === 'less'}
			less than {element.count}
		{:else if element.count === 1}
			a
		{:else}
			{element.count ?? '_error_'}
		{/if}

		<!-- { element.type } -->
		<span class="highlight" class:subtext={!element.value}>
			{element.value ? element.value : 'Select charachter(s)'}
		</span>

		{#if element.connector !== 'none'}
			{element.connector}
		{/if}
	{:else if element.param === 'input' && editMode}
		<Menu>
			<div class:hidden={element.count} on:click={() => (element.count = 1)}>Add a count</div>
			<div style="color: var(--error);" on:click={deleteBlock}>Delete</div>
		</Menu>

		that
		<select
			class="small"
			style="display: inline-block;width: 145px;margin-inline-start: .5rem;
			margin-block-end: .5rem;"
			placeholder={element.matchtype}
			bind:value={$globalState.searchRequest.search[index].matchtype}
		>
			<option value="contains">contains</option>
			<option value="does not contain">does not contain</option>
			<option value="is">is</option>
			<option value="is not">is not</option>
			<option value="begins">begins with</option>
			<option value="ends">ends with</option>
		</select>

		{#if (element.matchtype === 'contains' || element.matchtype === 'does not contain') && element.count !== undefined}
			<!-- <input type="text" bind:value={element.count}> -->
			<select
				class="small"
				style="display: inline-block;width: 120px;margin-inline-start: .5rem;
				margin-block-end: .5rem;"
				placeholder={element.counttype}
				bind:value={element.counttype}
			>
				<option value="equal">exactly</option>
				<option value="greater">greater than</option>
				<option value="less">less than</option>
			</select>
			<input
				type="number"
				class="small"
				style="width: 50px;"
				placeholder="count"
				bind:value={$globalState.searchRequest.search[index].count}
			/>
		{:else}
			&nbsp;a
		{/if}

		<!-- <input
			type="text"
			class="small"
			style="width: 150px;"
			placeholder="value"
			bind:value={$globalState.searchRequest.search[index].value}
		/> -->

		<InputKeyboard {index} />

		{#if $globalState.searchRequest.search.length - 1 !== index}
			<select
				class="small"
				style="display: inline-block;width: 120px;margin-inline-start: .5rem;
				margin-block-end: .5rem;"
				placeholder={element.connector}
				bind:value={element.connector}
			>
				<!-- <option value="none">no connector</option> -->
				<option value="and">and</option>
				<option value="or">or</option>
				<!-- <option value="distance">distance</option> -->
			</select>
		{/if}
	{:else if element.param === 'abstract'}
		{#if element.matchtype === 'begins' || element.matchtype === 'ends'}
			that {element.matchtype} with
		{:else if element.matchtype === 'contains' || element.matchtype === 'does not contain'}
			that {element.matchtype} within
		{:else}
			{element.matchtype}
		{/if}

		{#if element.counttype === 'greater' && element.count === 0}
			any count of a
		{:else if element.counttype === 'greater'}
			greater than {element.count}
		{:else if element.counttype === 'less'}
			less than {element.count}
		{:else if element.count === 1}
			a
		{:else}
			{element.count}
		{/if}

		{element.type}, which

		{#if element.connector !== 'none'}
			{element.connector}
		{/if}
	{:else if element.param === 'distance'}
		{element.count}

		{element.type}
		{element.distancetype}
		{element.count}

		{#if element.connector !== 'none'}
			{element.connector}
		{/if}
	{:else if element.param === ''}
		&nbsp;
	{:else}
		{element.param} is not a valid param value
	{/if}

	{#if typeof $globalState.searchRequest.search?.[index + 1] !== 'undefined' && $globalState.searchRequest.search[index + 1].level !== element.level}
		is either
	{/if}
</div>

{#if typeof $globalState.searchRequest.search?.[index + 1] !== 'undefined' && $globalState.searchRequest.search[index + 1].level !== element.level}
	<div class="spacer" />
{/if}

<style>
	.option {
		position: relative;
		/* align-items: baseline;
		justify-content: flex-start; */
		margin: 0.25rem calc(var(--topPadding) / 2);
		padding: 0.5rem calc(var(--topPadding) / 2);
		border-radius: var(--borderRadius);
		cursor: pointer;
		user-select: none;
	}
	.hidden {
		display: none;
	}
	.option:hover {
		background-color: var(--secondary-bg-color);
	}
	.option.editMode {
		padding-bottom: 0;
		background-color: var(--tertiary-bg-color);
	}
	.highlight {
		background-color: var(--gray-shade-1);
		border-radius: var(--borderRadius);
		padding: 0.25rem 0.5rem;
	}
	.spacer {
		margin: 0 4rem 0.5rem;
		border-bottom: 2px solid var(--tertiary-bg-color);
	}

	/* remove input:number arrows */
	input::-webkit-outer-spin-button,
	input::-webkit-inner-spin-button {
		-webkit-appearance: none;
		margin: 0;
	}
	input[type='number'] {
		-moz-appearance: textfield;
	}
</style>
