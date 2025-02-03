    const loggedIn = true;
    const rol = "admin";

    export const UserLoggin = loggedIn ? (
        <div className="flex justify-between items-center">
            <div className="flex items-center">
                <img src="https://i.ibb.co/4j3m5kH/logo-1.png" alt="logo" className="w-8 h-8" />
                <h1 className="text-2xl font-bold ml-3">TrackMyFix</h1>
            </div>
            <div className="flex items-center">
                <div className="relative">
                    <button className="flex items-center justify-center w-8 h-8 rounded-full bg-gray-100 hover:bg-gray-200 transition duration-300 ease-in-out">
                        <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
                            <path d="M17.414 2.586a2 2 0 00-2.828 0L7 10.172V13h2.828l7.586-7.586a2 2 0 000-2.828z" />
                            <path
                                fillRule="evenodd"
                                d="M2 6a2 2 0 012-2h4a1 1 0 010 2H4v10h10v-4a1 1 0 112 0v4a2 2 0 01-2 2H4a2 2 0 01-2-2V6z"
                                clipRule="evenodd"
                            />
                        </svg>
                    </button>
                    <div className="absolute right-0 top-0 z-10 py-2 px-4 w-56 rounded-md shadow-lg bg-white ring-1 ring-black ring-opacity-5 focus:outline-none">
                        <form action="#" className="flex flex-col">
                            <input
                                type="text"
                                placeholder="Buscar"
                                className="w-full px-4 py-2 text-sm text-gray-900 bg-white border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-offset-gray-100 focus:ring-blue-500"
                            />
                            <button
                                type="submit"
                                className="w-full px-4 py-2 text-sm font-medium text-center text-white bg-blue-600 rounded-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-offset-gray-100 focus:ring-blue-500"
                            >
                                Buscar
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    ) : (
        <div className="flex justify-between items-center">
            <div className="flex items-center">
                <img src="https://i.ibb.co/4j3m5kH/logo-1.png" alt="logo" className="w-8 h-8" />
                <h1 className="text-2xl font-bold ml-3">TrackMyFix</h1>
            </div>
            <div className="flex items-center">
                <button className="flex items-center justify-center w-8 h-8 rounded-full bg-gray-100 hover:bg-gray-200 transition duration-300 ease-in-out">
                    <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
                        <path d="M17.414 2.586a2 2 0 00-2.828 0L7 10.172V13h2.828l7.586-7.586a2 2 0 000-2.828z" />
                        <path
                            fillRule="evenodd"
                            d="M2 6a2 2 0 012-2h4a1 1 0 010 2H4v10h10v-4a1 1 0 112 0v4a2 2 0 01-2 2H4a2 2 0 01-2-2V6z"
                            clipRule="evenodd"
                        />
                    </svg>
                </button>
            </div>
        </div>
    );
