<script lang="ts">
// param: string;
// type: string;
// connector: string;
// value?: string;
// count?: number;
// counttype?: string;
// distancetype?: string;
// matchtype?: string;
// level: number;


	// import { globalState } from '../../globalState'
	import { clickedOutside } from '$lib/ui/clickedOutside'
	// import Menu from '$lib/Menu.svelte'
	// import InputKeyboard from './_InputKeyboard.svelte'

	export let element: SearchParam, index: number
</script>

<div>{JSON.stringify(element)}</div>