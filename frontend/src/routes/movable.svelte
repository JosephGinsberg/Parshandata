<script>
	let list = [
		{ id: 1, value: "red" },
		{ id: 2, value: "green" },
		{ id: 3, value: "blue" },
		{ id: 4, value: "cyan" },
	];

	let mouseYCoordinate = null; // pointer y coordinate within client
	let distanceTopGrabbedVsPointer = null;

	let draggingItem = null;
	let draggingItemId = null;
	let draggingItemIndex = null;

	let hoveredItemIndex = null;

	// $: {
	//     // prevents the ghost flickering at the top
	//     if (mouseYCoordinate == null || mouseYCoordinate == 0) {
	//         // showGhost = false;
	//     }
	// }

	$: {
		if (
			draggingItemIndex != null &&
			hoveredItemIndex != null &&
			draggingItemIndex != hoveredItemIndex
		) {
			// swap items
			[list[draggingItemIndex], list[hoveredItemIndex]] = [
				list[hoveredItemIndex],
				list[draggingItemIndex],
			];

			// balance
			draggingItemIndex = hoveredItemIndex;
		}
	}

	let container = null;
</script>

<div class="container" bind:this={container}>
	{#if mouseYCoordinate}
		<div
			class="item ghost"
			style="top: {mouseYCoordinate + distanceTopGrabbedVsPointer}px; background: {draggingItem.value};">
			{draggingItem.value}
		</div>
	{/if}

	{#each list as item, index (item)}
		<div
			class="item {draggingItemId == item.id ? 'invisible' : ''}"
			style="background: {item.value};"
			draggable="true"
			on:dragstart={(e) => {
				mouseYCoordinate = e.clientY;
				//console.log('dragstart', mouseYCoordinate);

				draggingItem = item;
				draggingItemIndex = index;
				draggingItemId = item.id;

				distanceTopGrabbedVsPointer = 
					e.target.getBoundingClientRect().y -
					e.target.parentElement.getBoundingClientRect().y -
					e.clientY
			}}
			on:drag={(e) => {
				const parentPosition = e.target.parentElement.getBoundingClientRect()
				if(e.clientY > parentPosition.top && e.clientY < parentPosition.bottom){
					mouseYCoordinate = e.clientY;
				}
				console.log('drag', mouseYCoordinate, parentPosition.top, parentPosition.bottom, draggingItemId);
			}}
			on:dragover={(e) => {
				hoveredItemIndex = index;
			}}
			on:dragend={(e) => {
				console.log('dragend', mouseYCoordinate);
				//console.log('\n');

				// mouseYCoordinate = e.clientY;
				mouseYCoordinate = 0;

				draggingItemId = null; // makes item visible
				hoveredItemIndex = null; // prevents instant swap
			}}>
			{item.value}
		</div>
	{/each}
</div>

<!-- {draggingItemIndex}
{hoveredItemIndex}
<br />
mouseYCoordinate:
{mouseYCoordinate}
distanceTopGrabbedVsPointer:
{distanceTopGrabbedVsPointer}
<br />
{showGhost}
<br /> -->

{JSON.stringify(list)}

<style>
	.container {
		background: #ccc;
		padding: 10px;
	}

	.item {
		width: 300px;
		background: white;
		padding: 10px;
		margin-bottom: 10px;
		cursor: grab;
	}

	.ghost {
		margin-bottom: 10px;
		pointer-events: none;
		z-index: 99;
		position: absolute;
		top: 0px;
		left: 10;
	}

	.invisible {
		opacity: 0;
	}
</style>
