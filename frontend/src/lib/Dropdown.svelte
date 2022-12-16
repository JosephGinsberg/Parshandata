<script lang="ts">
	import { createEventDispatcher } from 'svelte'
	import Icon from '$lib/Icon.svelte'
	import { clickedOutside } from '$lib/clickedOutside'

	export let classes: string = 'default',
		style: string = '',
		options: dropdownInput[] | any,
		placeholder: string = 'Select an option',
		returnOriginal: boolean = false,
		search: boolean = false

	const dispatch = createEventDispatcher()
	let openDropdown = 0,
		searchTerm = '',
		checkboxes: any = []

	const valueChange = () => {
			let value: string[] | dropdownInput[] | any
			if (returnOriginal) {
				value = options
			} else {
				value = []
				options.forEach((option: dropdownInput) => {
					if (!option.checked) return
					value.push(option.value)
				})
			}
			dispatch('change', { value })
		},
		filter = (choices: dropdownInput[]) => {
			try {
				const tempRegex = new RegExp(searchTerm, 'i')
			} catch {
				return choices
			}
			choices.forEach(choice => {
				const tempRegex = new RegExp('^' + searchTerm, 'i')
				const searchValues = choice?.text + '|' + choice?.value ?? ''
				choice.display = searchValues.match(tempRegex) ? true : false
			})
			return choices
		}
	// if user chose an option, keep search results
	$: if (searchTerm) options = filter(options)
</script>

<div class="container">
	<div
		class="dropdown row {classes}"
		{style}
		on:click={() => {
			if (new Date().getTime() - openDropdown > 20) openDropdown = 1
		}}
	>
		<span>{placeholder}</span>
		<Icon name="expand" style="fill: var(--primary-txt-color);" />
	</div>

	<!-- append passed in children -->
	{#if openDropdown === 1}
		<div
			class="menu card"
			use:clickedOutside
			on:outclick={() => (openDropdown = new Date().getTime())}
		>
			{#if search}
				<input
					type="text"
					placeholder="Search"
					autocomplete="off"
					bind:value={searchTerm}
					on:keyup={() => (options = filter(options))}
				/>
				<div class="spacer" />
			{/if}
			{#each options as option, id (option)}
				{@const idString = String.fromCharCode(id + 64)}
				{#if option.text && (option.display === undefined || option.display)}
					<div class="option row">
						<input
							type="checkbox"
							id={idString}
							value={option.value ?? option}
							bind:this={checkboxes[idString]}
							bind:checked={options[id].checked}
							on:change={valueChange}
						/>
						{#if options[id].checked}
							<Icon
								height="22px"
								width="22px"
								style="fill: var(--blue-primary);"
								name="checkbox_checked"
								on:click={() => checkboxes[idString].click()}
							/>
						{:else if options[id]?.partial}
							<Icon
								height="22px"
								width="22px"
								style="fill: var(--blue-primary);"
								name="checkbox_partial"
								on:click={() => checkboxes[idString].click()}
							/>
						{:else}
							<Icon
								height="22px"
								width="22px"
								name="checkbox_unchecked"
								on:click={() => checkboxes[idString].click()}
							/>
						{/if}
						<label for={idString}>{option.text ?? option.value}</label>
					</div>
				{:else if !option.text && !searchTerm}
					<div class="spacer" />
				{/if}
			{/each}
		</div>
	{/if}
</div>

<style>
	.dropdown {
		height: 2em;
		padding: 0 8px;
		background-color: var(--secondary-bg-color);
		border: 2px solid var(--gray-shade-2);
		border-radius: var(--borderRadius);
		cursor: pointer;
		user-select: none;

		text-overflow: ellipsis;
		white-space: nowrap;
	}
	.dropdown ::first-letter {
		/* text-transform: uppercase; */
		user-select: none;
	}
	.dropdown.small {
		font-size: 0.875rem;
		padding: 0 0.5rem;
		box-sizing: border-box;
	}

	.menu {
		position: absolute;
		top: 100%;
		left: 0px;
		max-height: 250px;
		width: 100%;
		overflow: auto;
		box-sizing: border-box;
		margin-top: 2px;
		padding: calc(var(--topPadding) / 2) var(--topPadding);
		background-color: var(--secondary-bg-color);
		z-index: 10;
	}
	.menu input[type='text'] {
		max-width: 100%;
		margin: 0;
		padding: 0;
		border: 0px;
		background-color: var(--secondary-bg-color);
	}
	.menu input[type='text']:focus {
		outline: none;
	}
	.menu .option {
		justify-content: flex-start;
		cursor: pointer;
		user-select: none;
	}
	.option input {
		display: none;
	}
	.option label {
		height: 100%;
		width: 100%;
		padding: calc(var(--topPadding) / 3) 0 calc(var(--topPadding) / 3) 8px;
		cursor: pointer;
	}
	.spacer {
		margin: 0.5rem;
		border-bottom: 2px solid var(--gray-shade-1);
	}
	.spacer + .spacer,
	.spacer:last-child {
		display: none;
	}
</style>
