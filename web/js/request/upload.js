import {baseUrl, invalidToken} from './common.js'

export const upload = (file) => {
    return new Promise((resolve, reject) => {
        const token = localStorage.getItem('token');
        if (!token) {
            const b = confirm("请先登陆");
            if (b) {
                window.open("http://localhost:8080/flea/view/login")
            }
            reject({message: "请先登陆"})
        }
        const data = new FormData()
        data.append("file", file, file.name)
        const xhr = new XMLHttpRequest()
        xhr.onload = () => {
            if (xhr.status === 200) {
                const {path} = JSON.parse(xhr.responseText)
                resolve(path)
            } else {
                invalidToken(JSON.parse(xhr.responseText))
                reject(xhr.responseText)
            }
        }
        xhr.open("POST", baseUrl + '/upload')
        xhr.send(data)
    })
}