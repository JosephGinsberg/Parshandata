<script lang="ts">
	import SearchContainer from "./_SearchContainer.svelte"
	import ResultsContainer from "./_ResultsContainer.svelte"
	import ParameterPanel from "./_ParameterPanel.svelte"

	let books: string[] = [
		"Genesis",
		"Exodus"
	],
	searchJSON: searchParam[] = [
		{
			param: "condition",
			type: "contains",
			connector: "or",
			level: 1
		},{
			param: "input",
			type: "letter",
			connector: "none",
			value: "י",
			count: 2,
			level: 2
		}
	],
	searchRequest: searchRequest | JSON = {
		books,
		keriUkesiv: false,
		taamTachton: false,
		display: 'every',
		splitBy: 'pasuk',
		remove: [],
		search: searchJSON
	},
	searchStatus: searchResponse | Promise<searchResponse> = {
		ok: true,
		msg: "",
		runtime: 0,
		matches: [
			{
				bookname: "Genesis",
				fullverse: "וַיַּ֣עַשׂ אֱלֹהִים֩ אֶת־חַיַּ֨ת הָאָ֜רֶץ לְמִינָ֗הּ וְאֶת־הַבְּהֵמָה֙ לְמִינָ֔הּ וְאֵ֛ת כָּל־רֶ֥מֶשׂ הָֽאֲדָמָ֖ה לְמִינֵ֑הוּ וַיַּ֥רְא אֱלֹהִ֖ים כִּי־טֽוֹב׃",
				pasuk: "25",
				perek: "1",
				splitvalue: "לְמִינָ֗הּ"
			},{
				bookname: "Exodus",
				fullverse: "וְאַחַ֗ר בָּ֚אוּ מֹשֶׁ֣ה וְאַהֲרֹ֔ן וַיֹּאמְר֖וּ אֶל־פַּרְעֹ֑ה כֹּֽה־אָמַ֤ר יְהוָה֙ אֱלֹהֵ֣י ",
				pasuk: "5",
				perek: "1",
				splitvalue: "וַיֹּאמְר֖וּ"
			},{
				bookname: "Leviticus",
				fullverse: "וְהִקְרִיב֞וֹ לִפְנֵ֤י יְהוָה֙ וְכִפֶּ֣ר עָלֶ֔יהָ וְטָהֲרָ֖ה מִמְּקֹ֣ר דָּמֶ֑יהָ זֹ֤את תּוֹרַת֙ הַיֹּלֶ֔דֶת לַזָּכָ֖ר א֥וֹ לַנְּקֵבָֽה",
				pasuk: "12",
				perek: "7",
				splitvalue: "וְהִקְרִיב֞וֹ"
			},{
				bookname: "Amos",
				fullverse: "דִּבְרֵ֣י עָמ֔וֹס אֲשֶׁר־הָיָ֥ה בַנֹּקְדִ֖ים מִתְּק֑וֹעַ אֲשֶׁר֩ חָזָ֨ה עַל־יִשְׂרָאֵ֜ל בִּימֵ֣י ׀ עֻזִיָּ֣ה",
				pasuk: "1",
				perek: "1",
				splitvalue: "דִּבְרֵ֣י עָמ֔וֹס"
			}
		]
	},
	count: string = '0'

	searchRequest = JSON.parse(`{"books":["Genesis","Exodus","Leviticus","Numbers","Deuteronomy"],"keriUkesiv":true,"taamTachton":true,"remove":[],"display":"every","splitBy":"pasuk","search":[{"param":"condition","type":"contains","connector":"none","level":1},{"param":"input","type":"trop","value":"legarmeh","matchtype":"contains","count":1,"counttype":"greater","connector":"distance","level":1},{"param":"distance","type":"before","distancetype":"greater","distance":1,"connector":"and","level":1},{"param":"distance","type":"before","distancetype":"less","distance":5,"connector":"none","level":1},{"param":"input","matchtype":"contains","type":"trop","value":"revie","count":2,"counttype":"equal","connector":"none","level":1}]}`)

	const fetchSearch = async () => {
		count = '#'
		const res: any = await fetch(window.location.protocol==='https:'? '/api/search': 'http://localhost:8080/api/search', {
			method: 'POST',
			headers: {
				'content-type': 'application/json'
			},
			body: JSON.stringify(searchRequest)
		}),
		json = await res.json()
		count = json.matches.length.toString()
		console.log('runtime: %s', json.runtime)
		return json
	}
	const runSearch: VoidFunction = () => searchStatus = fetchSearch()
	const updateSearch = (newRequest: JSON) => searchRequest = newRequest
</script>


<svelte:head>
	<title>Search | Parshandata</title>
	<meta name="description" content="A tool to search Tanach the way that you want to." />
</svelte:head>

<section class="fullHeight" class:fullWidth={false}>
	<h3>Search Tanach</h3>
	
	<div class="row">
		<SearchContainer search={searchRequest} {runSearch} {updateSearch}/>
		<ResultsContainer state={searchStatus} {count} />
		<ParameterPanel search={searchRequest} />
	</div>
</section>

<style>
	section{
		padding-top: 0;
		padding-bottom: 0;
	}
	.row{
		align-items: flex-start;
		justify-content: space-between;
		height: 100%;
		width: 100%;
		max-width: 100%;
		flex: 1 1 0;
	}
</style>