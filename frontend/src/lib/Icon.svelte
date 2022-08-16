<script lang="ts">
	export let name: string,
		width = '24px',
		height = '24px',
		margin = '0px',
		style = ''
</script>

<svg {width} {height} style="margin-right: {margin};{style}">
	<use href="/icons.svg#{name}" />
</svg>
