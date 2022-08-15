<script lang="ts">
	import { createEventDispatcher } from 'svelte'
	import Icon from '$lib/Icon.svelte'

	export let classes: string = 'default',
		style: string = '',
		options: dropdownInput[],
		placeholder: string = 'Select an option',
		returnOriginal: boolean = false,
		filter: boolean = false

	const dispatch: any = createEventDispatcher()
	let openDropdown: boolean = false

	const valueChange = () => {
		let value: string[] | dropdownInput[] | any
		if (returnOriginal) {
			value = options
		} else {
			value = []
			options.forEach(option => {
				if (!option.checked) return
				value.push(option.value)
			})
		}
		dispatch('change', { value })
	}
</script>

<div class="closeMenu" class:display={openDropdown} on:click={() => (openDropdown = false)} />

<div class="container" on:click={() => (openDropdown = true)}>
	<div class="dropdown row {classes}" {style}>
		<span>{placeholder}</span>
		<Icon name="expand" />
	</div>

	<!-- append passed in children -->
	{#if openDropdown}
		<div class="menu card">
			{#each options as option, id (option)}
				{#if option.text}
					<div class="option row">
						<input
							type="checkbox"
							id={id.toString() + '~'}
							value={option.value ?? option}
							bind:checked={options[id].checked}
							on:change={valueChange}
						/>
						<label for={id.toString() + '~'}>{option.text ?? option.value}</label>
					</div>
				{:else}
					<div class="spacer" />
				{/if}
			{/each}
		</div>
	{/if}
</div>

<style>
	.closeMenu {
		user-select: none;
	}
	.closeMenu.display {
		position: absolute;
		top: 0px;
		left: 0px;
		width: 100%;
		height: 100%;
		z-index: 10;
		background-color: transparent;
	}
	.dropdown {
		height: 2em;
		padding: 0 8px;
		border: solid 1px var(--lightText);
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
		background-color: var(--defaultBackground);
		z-index: 10;
	}
	.menu .option {
		justify-content: flex-start;
	}
	.option input {
		align-self: flex-end;
	}
	.option label {
		height: 100%;
		width: 100%;
		padding: calc(var(--topPadding) / 5) 0 calc(var(--topPadding) / 5) 8px;
		cursor: pointer;
		user-select: none;
	}
	.spacer {
		margin: 0.5rem;
		border-bottom: 2px solid var(--disabledText);
	}
</style>
