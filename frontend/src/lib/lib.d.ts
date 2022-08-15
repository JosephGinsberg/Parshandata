interface notification {
	urgency: number
	msg: string
}

interface dropdownInput {
	checked: boolean
	value: string
	text?: string
	originalValue?: boolean
	grouping?: string[]
	disabled?: boolean
}
