<script lang="ts">
	export let placeholder: string, options: any

	options = options.filter((option: any, index: number) => {
		option.index = index
		return option
	})

	// let checked: string[] = []
	// $: {
	// 	checked = []
	// 	options.forEach((option: any) => {
	// 		console.log(option)
	// 		if(option.checked)
	// 			checked.push(option.value)
	// 	})
	// 	console.log(checked)
	// }

	let optionsCopy = options,
		changedOption: any

	const evaluateValue = () => {
			// console.log(options[changedOption])

			// value = e.target.value,
			// changed = options[i]
			let selected: string[] = [],
				remove: string[] = []

			options.forEach((option: any) => {
				if (
					option.checked &&
					typeof option.value === 'string' &&
					selected.indexOf(option.value) === -1
				)
					selected.push(option.value)
				else if (option.checked && typeof changedOption.value === 'object')
					selected = [...selected, ...option.value]
				else if (!option.checked && typeof changedOption.value === 'object')
					remove = [...remove, ...changedOption.value]
			})

			selected.filter(value => remove.indexOf(value) >= 0)

			console.log(selected)
		},
		clicked = (e: any) => (changedOption = options[Number(e.target.id)]),
		filter = () => {
			const tempRegex = new RegExp(searchTerm, 'i')
			optionsCopy = options.filter((option: any, index: number) => {
				if (option?.text.match(tempRegex)) return option
			})
		}

	let searchTerm = ''
</script>

<div>{placeholder}</div>

<input type="text" bind:value={searchTerm} on:keyup={filter} placeholder="Search books" />

{#each optionsCopy as option, i}
	{@const id = option.index}
	<label for={id}>
		<input
			type="checkbox"
			{id}
			bind:checked={option.checked}
			on:click={clicked}
			on:change={evaluateValue}
		/>

		{option.checked}&nbsp;&nbsp;&nbsp;&nbsp;{option.text ?? option.value}<br />
	</label>
{/each}

<style>
	label {
		user-select: none;
	}
</style>
