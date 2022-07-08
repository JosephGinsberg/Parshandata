<script lang="ts">
	export let state: searchResponse | Promise<searchResponse> | any,
	count: string

	const downloadResults: any | VoidFunction = () => {
		let dataString: string = 'data:text/plain;charset=utf-8,',
		filename: string = ''


		// const data: object = state.then(res: any => res.json())
		// dataString += JSON.stringify(data))

		// console.log(dataString)
		
		// initiate download
		// let link: HTMLElement = document.createElement('a')
		// link.setAttribute('href', encodeURI(dataString))
		// link.setAttribute('download', filename)
		// link.click()
		// console.log('')
	}
</script>

<div class="container">
	<div class="header row">
		<div>
			Results
			<span class="subtext">• {count}</span>
		</div>
		<div class="options row">
			<!-- <button on:click={downloadResults}>
				<img class="icon" src="download.svg" alt="download">
			</button> -->
		</div>
	</div>
	<div class="body">
		{#await state}
			<div class="result">Loading...</div>
		{:then data}
			{#if data.runtime}
				{#each data.matches as match}
					<div class="result">
						<div class="fullverse">
							{#if match.splitvalue}
								{match.fullverse.split(match.splitvalue)[0]}<b>{match.splitvalue}</b>{match.fullverse.split(match.splitvalue)[1]}
							{:else}
								{match.fullverse}
							{/if}
						</div>
						<div class="location">{match.bookname.replace('_', ' ')} {match.perek}:{match.pasuk}</div>
					</div>
				{:else}
					<div class="result">No results found</div>
				{/each}
			{:else}
				<div class="result">No search entered</div>
			{/if}
		{:catch error}
			<div class="result" style="color: red">{error.message}</div>
		{/await}
	</div>
</div>

<style>
	.container{
		height: 100%;
		max-height: 100%;
		overflow-y: hidden;
		width: 45%;
		max-width: 45%;
		box-sizing: border-box;
		background-color: rgb(250, 250, 255);
		border: 2px solid var(--disabledText);
		border-radius: var(--borderRadius);
		/* background-image: url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 32 32' width='32' height='32' fill='none' stroke='%23f1f5f9'%3e%3cpath d='M0 .5H31.5V32'/%3e%3c/svg%3e"); */
	}
	.container .header, .container .body{
		padding: var(--topPadding);
	}
	.container .header{
		border-bottom: 2px solid var(--disabledText);
	}
	.container .body{
		max-height: 84%;
		overflow-y: auto;
	}
	.container .result:not(:last-of-type){
		padding-bottom: calc(var(--topPadding) / 2);
		border-bottom: 2px solid var(--disabledText);
	}
	.container .result:not(:first-of-type){
		padding-top: calc(var(--topPadding) / 2);
	}
	.container .fullverse{
		font-family: 'david';
		font-size: 20px;
		text-align: right;
		padding-bottom: calc(var(--topPadding) / 2.5);
	}
</style>