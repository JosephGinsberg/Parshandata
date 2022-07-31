<script lang="ts">
	import { createEventDispatcher } from 'svelte'
	import Icon from '$lib/Icon.svelte'

	let value: string[] | string = ''
	const dispatch: any = createEventDispatcher()
	const transferClick = () => dispatch('change', { value })

	export let classes: string = 'default',
		style: string = '',
		options: dropdownInput[] | string[] = [],
		placeholder: string = 'Select an option',
		multiple: boolean = false

	let openDropdown: boolean = false,
		overRide: boolean = false,
		forceClose: boolean = false,
		inputElement: HTMLInputElement

	const focused = () => {
		if (forceClose) {
			overRide = false
			openDropdown = false
			forceClose = false
			inputElement.blur()
			return
		}

		openDropdown = true
		if (overRide) setTimeout(() => (overRide = false), 5)
	}
	const valueChange = () => {
		if (!multiple) {
			setTimeout(() => inputElement.blur(), 150)
		} else {
			value = []
			options.forEach(option => {
				if (!option.checked) return
				value.push(option.value)
			})
		}
		transferClick()
	}
</script>

<input
	type="text"
	bind:this={inputElement}
	on:focus={focused}
	on:blur={() => (openDropdown = false)}
/>

<div
	class="container"
	on:pointerdown={() => (overRide = true)}
	on:click={() => inputElement.focus()}
>
	<div
		class="dropdown row {classes}"
		{style}
		on:pointerdown={() => (forceClose = openDropdown ? true : false)}
	>
		<span>{placeholder}</span>
		<Icon name="expand" />
	</div>

	<!-- append passed in children -->
	{#if openDropdown || overRide}
		<div class="menu card" class:open={openDropdown || overRide}>
			{#each options as option, id (option)}
				<div class="option row">
					<!-- make it simpler by seperating functions for radio buttons and checkboxes -->
					{#if multiple}
						<input
							type="checkbox"
							id={id.toString()}
							value={option.value ?? option}
							bind:checked={option.checked}
							on:change={valueChange}
						/>
					{:else}
						<input
							type="radio"
							id={id.toString()}
							value={option.value ?? option}
							bind:group={value}
							on:change={valueChange}
						/>
					{/if}
					<label for={id.toString()}>{option.text ?? option}</label>
				</div>
			{/each}
		</div>
	{/if}
</div>

<style>
	input[type='text'] {
		display: block;
		height: 0px;
		width: 0px;
		margin: 0;
		padding: 0;
		border: none;
		opacity: 0;
	}

	.dropdown {
		height: 2em;
		padding: 0 8px;
		border: solid 1px var(--lightText);
		border-radius: var(--borderRadius);
		cursor: pointer;
		user-select: none;
	}
	.dropdown ::first-letter {
		text-transform: uppercase;
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
		z-index: 100;
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
	/* .container:active .option, .container:focus-visible .option{
		display: block;
	} */

	/* button{
		position: relative;
		display: inline-flex;
		align-items: center;
		padding: 0 .75rem;
		border: none;
		border-radius: var(--borderRadius);
		text-align: center;
		font-size: 1rem;
		height: 2em;
		white-space: nowrap;
		cursor: pointer;
		-webkit-user-select: none;
		-ms-user-select: none;
		user-select: none;
	}
	button:hover{
		transition: background-color 0.12s ease-out, color 0.12s ease-out;
	} */
</style>
