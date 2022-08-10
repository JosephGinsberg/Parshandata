<script lang="ts">
	import { globalState } from '../../globalState'
	import Dropdown from '$lib/Dropdown.svelte'

	export let element: SearchParam, index: number

	let searchRequest: SearchRequest
	globalState.subscribe(value => ({ searchRequest } = value))
	const updateEl = () => {
			globalState.update(state => {
				state.searchRequest.search[index] = element
				return state
			})
		},
		updateChange = (e: any) => {
			element.count = Number(e.target?.value)
			updateEl()
		}
</script>

<div class="option row {element.param}" id={index.toString()}>
	<!-- <img class="handle" src="/move.svg" alt="" /> -->

	{#if element.param === 'condition'}
		<div>that {element.type} a</div>
		<!-- that
		<Dropdown classes="small" placeholder="does not contain" style="margin-left: 8px;" options={[]} /> -->
	{:else if element.param === 'input'}
		that
		<select
			class="small"
			style="display: inline-block;width: 145px;margin-inline-start: .5rem;
			margin-block-end: .5rem;"
			placeholder={element.matchtype}
			bind:value={searchRequest.search[index].matchtype}
			on:change={updateEl}
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
				on:change={updateEl}
			>
				<option value="equal">exactly</option>
				<option value="greater">greater than</option>
				<option value="less">less than</option>
			</select>
			<select
				class="small"
				style="display: inline-block;width: 55px;margin-inline-start: .5rem;
				margin-block-end: .5rem;"
				placeholder={element.count?.toString()}
				value={element.count?.toString()}
				on:change={updateChange}
			>
				<option value="0">0</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
			</select>
		{:else}
			&nbsp;a
		{/if}

		<Dropdown
			classes="small"
			placeholder={element.value}
			style="margin-left: 8px;
		margin-block-end: .5rem;"
			options={[]}
		/>
		{#if searchRequest.search.length -1 !== index}
			<select
				class="small"
				style="display: inline-block;width: 120px;margin-inline-start: .5rem;
				margin-block-end: .5rem;"
				placeholder={element.connector}
				bind:value={element.connector}
				on:change={updateEl}
			>
				<option value="none">no connector</option>
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
	.option {
		/* display: inline-flex; */
		margin-block-start: 1rem;
		justify-content: flex-start;
		flex-wrap: wrap;
	}
	/* .option .handle {
		display: inline-block;
		height: 24px;
		width: 24px;
		margin-inline-end: 0.5rem;
		cursor: pointer;
		opacity: 0;
	}
	.option:hover .handle {
		opacity: 1;
	} */
</style>
