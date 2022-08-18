<script lang="ts">
	import { globalState } from '../../globalState'

	export let element: SearchParam, index: number
</script>

<div class="option">
	{#if element.param === 'input'}
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
			{element.count}
		{/if}

		<!-- { element.type } -->
		<span class="highlight">&nbsp;{element.value}&nbsp;</span>

		{#if element.connector !== 'none'}
			{element.connector}
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

<div
	style="display: none;"
	class="option row {element.param}"
	id={index.toString()}
	tabindex="-1"
	on:focus={() => console.log('focused')}
	on:blur={() => console.log('blur')}
>
	<!-- <img class="handle" src="/move.svg" alt="" /> -->

	{#if element.param === 'condition'}
		<!-- <div>that {element.connector} a</div> -->
		* condition
	{:else if element.param === 'input'}
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

		{#if element.matchtype === 'contains' || element.matchtype === 'does not contain'}
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
				type="text"
				class="small"
				style="width: 50px;"
				placeholder="count"
				bind:value={$globalState.searchRequest.search[index].count}
			/>
		{:else}
			&nbsp;a
		{/if}

		<!-- <Dropdown
			classes="small"
			placeholder={element.value}
			style="margin-left: 8px;
		margin-block-end: .5rem;"
			options={[]}
		/> -->
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
		<div>abstract</div>
	{:else if element.param === 'distance'}
		<div>
			at a distance of {element.count}
			{element.type}
			{#if element.connector != 'none'}{element.connector}{/if}
		</div>
	{/if}
</div>

<style>
	/* .option {
		/* display: inline-flex; *
		margin-block-start: 1rem;
		justify-content: flex-start;
		flex-wrap: wrap;
	} */

	.option {
		margin: 0.25rem calc(var(--topPadding) / 2);
		padding: 0.5rem calc(var(--topPadding) / 2);
		cursor: pointer;
	}
	.option:hover {
		background-color: var(--tertiary-bg-color);
		background-color: var(--secondary-bg-color);
		border-radius: var(--borderRadius);
	}
	.highlight {
		background-color: var(--gray-shade-1);
		border-radius: var(--borderRadius);
		padding: 4px;
	}
	.spacer {
		margin: 0 4rem 0.5rem;
		border-bottom: 2px solid var(--tertiary-bg-color);
	}
</style>
