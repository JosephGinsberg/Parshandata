export const clickedOutside = (node: HTMLElement) => {
	const handleClick = (e: any) => {
		if (node.contains(e.target)) return
		node.dispatchEvent(new CustomEvent('outclick'))
	}

	document.addEventListener('click', handleClick, true)

	return {
		destroy() {
			document.removeEventListener('click', handleClick, true)
		}
	}
}
