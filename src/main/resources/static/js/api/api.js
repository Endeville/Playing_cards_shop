const host="http://localhost:8000"

async function request(url, options){
    try{
        const response=await fetch(host + url,options);

        try{
            return await response.json();
        }catch(err){
            return response;
        }
    }catch(err){
        throw err;
    }
}

export async function get(url){
    return request(url,'get');
}

export async function post(url){
    return request(url, "post");
}

export async function put(url){
    return request(url, "put");
}

export async function del(url){
    return request(url, "delete");
}