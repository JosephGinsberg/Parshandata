const base = 'https://parshandata.onrender.com'; // import.meta.env.DEV
// ? `${import.meta.env.VITE_DOMAIN_BASE}/search`
// : window.location.hostname === 'localhost:8080/search';

export function api(method: string, resource: string, data?: Record<string, unknown | any>) {
	return fetch(`${base}/${resource}`, {
		method,
		headers: {
			'content-type': 'application/json'
		},
		body: data && JSON.stringify(data)
	});
}
